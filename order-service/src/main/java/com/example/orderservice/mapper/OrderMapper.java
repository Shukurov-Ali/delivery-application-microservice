package com.example.orderservice.mapper;

import com.example.orderservice.dao.entity.OrderEntity;
import com.example.orderservice.dto.OrderRequestDto;
import com.example.orderservice.dto.OrderResponseDto;
import com.example.orderservice.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderMapper {
    public static OrderEntity mapOrderToOrderEntity(OrderRequestDto dto) {
        var entity = new OrderEntity();
        entity.setPickupAddress(dto.getPickupAddress());
        entity.setDeliveryAddress(dto.getDeliveryAddress());
        entity.setStatus(OrderStatus.CREATED);
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public static OrderResponseDto mapToResponseDto(OrderEntity entity) {
        var responseDto = new OrderResponseDto();
        responseDto.setPickupAddress(entity.getPickupAddress());
        responseDto.setDeliveryAddress(entity.getDeliveryAddress());
        responseDto.setCourierId(entity.getCourierId());
        responseDto.setStatus(entity.getStatus());
        responseDto.setDeliveryPrice(entity.getDeliveryPrice());
        responseDto.setStatus(entity.getStatus());
        responseDto.setCreatedAt(entity.getCreatedAt());
        responseDto.setDeliveredAt(entity.getDeliveredAt());
        return responseDto;
    }
}
