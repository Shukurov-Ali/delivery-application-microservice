package com.example.orderservice.dto;

import com.example.orderservice.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponseDto {
    private String pickupAddress;

    private String deliveryAddress;

    private Long courierId;

    private BigDecimal deliveryPrice;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime deliveredAt;
}
