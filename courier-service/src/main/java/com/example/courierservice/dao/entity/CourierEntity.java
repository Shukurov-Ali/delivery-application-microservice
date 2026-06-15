package com.example.courierservice.dao.entity;

import com.example.courierservice.enums.CourierStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="couriers")
@Entity
public class CourierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name="full_name")
    private String fullName;
    @Column(name="phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private CourierStatus status;

}
