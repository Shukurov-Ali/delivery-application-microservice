package com.example.paymentservice.dao.repository;

import com.example.paymentservice.dao.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
    Optional<PaymentEntity> findByOrderId(Long orderId);

}
