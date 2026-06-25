package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentResponseDto;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/pending")
    public void createPendingPayment(@RequestParam Long orderId,
                                     @RequestParam Long courierId,
                                     @RequestParam BigDecimal amount) {
        paymentService.createPendingPayment(orderId, courierId, amount);
    }

    @GetMapping
    public List<PaymentResponseDto> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @PostMapping("/complete/{orderId}")
    public void completePayment(@PathVariable Long orderId) {
         paymentService.completePayment(orderId);
    }
}
