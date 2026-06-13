package com.example.courierservice.dto;

import com.example.courierservice.enums.CourierStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourierResponseDto {
    private String fullName;
    private String phoneNumber;
    private CourierStatus status;
}
