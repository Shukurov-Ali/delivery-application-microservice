package com.example.courierservice.dao.repository;

import com.example.courierservice.dao.entity.CourierEntity;
import com.example.courierservice.enums.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Long> {
    Optional<CourierEntity> findFirstByStatus(CourierStatus status);
    boolean existsByPhoneNumber(String phoneNumber);
}
