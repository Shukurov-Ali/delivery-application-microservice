package com.example.paymentservice.mapper;

import com.example.paymentservice.dao.entity.PaymentEntity;
import com.example.paymentservice.dto.PaymentResponseDto;

public class PaymentMapper {
    public static PaymentResponseDto mapToResponseDto(PaymentEntity entity) {
        var responseDto = new PaymentResponseDto();

        responseDto.setOrderId(entity.getOrderId());
        responseDto.setCourierId(entity.getCourierId());
        responseDto.setAmount(entity.getAmount());
        responseDto.setStatus(entity.getStatus());
        responseDto.setCreatedAt(entity.getCreatedAt());
        responseDto.setCompletedAt(entity.getCompletedAt());

        return responseDto;
    }
}
