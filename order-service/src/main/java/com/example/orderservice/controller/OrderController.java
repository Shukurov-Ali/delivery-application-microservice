package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto createOrder(@Valid @RequestBody OrderRequestDto dto){
    return  orderService.createOrder(dto);
    }
    @GetMapping
    public List<OrderResponseDto> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/{id}")
    public OrderResponseDto getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }
    @PostMapping("/{id}/picked-up")
    public OrderResponseDto markAsPickedUp(@PathVariable Long id){
        return orderService.markAsPickedUp(id);
    }
    @PostMapping("/{id}/delivered")
    public OrderResponseDto markAsDelivered(@PathVariable Long id) {
        return orderService.markAsDelivered(id);
    }
}
