package com.example.paymentservice.service;

import com.example.paymentservice.dao.entity.PaymentEntity;
import com.example.paymentservice.dao.repository.PaymentRepository;
import com.example.paymentservice.dto.PaymentResponseDto;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void createPendingPayment(Long orderId, Long courierId, BigDecimal amount) {
        var payment= new PaymentEntity();
        payment.setOrderId(orderId);
        payment.setCourierId(courierId);
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(payment);
    }
    public List<PaymentResponseDto> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::mapToResponseDto)
                .toList();
    }
    public PaymentResponseDto completePayment(Long orderId) {
        var payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found with orderId " + orderId));

        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setCompletedAt(LocalDateTime.now());

        paymentRepository.save(payment);

        return PaymentMapper.mapToResponseDto(payment);
    }

}
