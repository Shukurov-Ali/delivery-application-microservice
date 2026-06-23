package com.example.orderservice.service;

import com.example.orderservice.client.CourierClient;
import com.example.orderservice.dao.entity.OrderEntity;
import com.example.orderservice.dao.repository.OrderRepository;
import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.exception.OrderAlreadyDeliveredException;
import com.example.orderservice.exception.OrderAlreadyPickedUpException;
import com.example.orderservice.exception.OrderInvalidStatusException;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CourierClient courierClient;


    public OrderResponseDto createOrder(OrderRequestDto dto) {
        var courier= courierClient.getAvailableCouriers();
        var order = OrderMapper.mapOrderToOrderEntity(dto);
        order.setCourierId(courier.getId());
        order.setDeliveryPrice(BigDecimal.valueOf(5));
        order.setStatus(OrderStatus.ASSIGNED);
        orderRepository.save(order);
        courierClient.markCourierAsBusy(courier.getId());
        log.info("Order created and assigned to courier. courierId={}", courier.getId());
        return OrderMapper.mapToResponseDto(order);
    }

    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().
                stream()
                .map(OrderMapper::mapToResponseDto)
                .toList();
    }

    public OrderResponseDto getOrderById(Long id) {
        var order= fetchOrderIfExist(id);
        return OrderMapper.mapToResponseDto(order);
    }

    public OrderResponseDto markAsPickedUp(Long id) {
        var order= fetchOrderIfExist(id);
        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new OrderAlreadyDeliveredException("Delivered order cannot be updated");
        }
        if (order.getStatus() == OrderStatus.PICKED_UP) {
            throw new OrderAlreadyPickedUpException("Order already picked up");
        }
        order.setStatus(OrderStatus.PICKED_UP);
        orderRepository.save(order);
        log.info("Order marked as picked up. orderId={}", id);
        return OrderMapper.mapToResponseDto(order);
    }


    public OrderResponseDto markAsDelivered(Long id) {
        var order = fetchOrderIfExist(id);
        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new OrderAlreadyDeliveredException("Delivered order cannot be updated");
        }
        if (order.getStatus() != OrderStatus.PICKED_UP) {
            throw new OrderInvalidStatusException("Order must be picked up before delivered");
        }
        order.setStatus(OrderStatus.DELIVERED);
        order.setDeliveredAt(LocalDateTime.now());
        orderRepository.save(order);
        courierClient.markCourierAsFree(order.getCourierId());
        log.info("Order delivered. orderId={}", id);
        return OrderMapper.mapToResponseDto(order);
    }

    private OrderEntity fetchOrderIfExist(Long id){
        return orderRepository.findById(id).orElseThrow(()->
                new OrderNotFoundException("Order Not Found with id: "+id));
    }

}
