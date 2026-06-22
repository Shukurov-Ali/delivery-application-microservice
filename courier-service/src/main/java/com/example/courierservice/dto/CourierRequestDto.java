package com.example.courierservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourierRequestDto {
        @NotBlank(message = "Full name is required")
        @Size(min = 2, max = 100, message = "Full name must be between 2 and 20 characters")
        private String fullName;

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^(\\+994)(50|51|55|70|77|99)[0-9]{7}$",
                message = "Phone number must be in valid +994 format (e.g. +994501234567)"
        )
        private String phoneNumber;
    }
