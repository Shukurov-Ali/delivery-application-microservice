package com.example.courierservice.controller;

import com.example.courierservice.dto.CourierRequestDto;
import com.example.courierservice.dto.CourierResponseDto;
import com.example.courierservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/couriers")
public class CourierController {
    private final CourierService courierService;

    @PostMapping
    public void createCourier(@RequestBody CourierRequestDto dto) {
        courierService.createCourier(dto);
    }

    @GetMapping
    public List<CourierResponseDto> getCouriers() {
        return courierService.getAllCouriers();
    }

    @GetMapping("/{id}")
    public CourierResponseDto getCourierById(@PathVariable Long id) {
        return courierService.getCourierById(id);
    }

    @GetMapping("/available")
    public CourierResponseDto getAvailableCourier() {
        return courierService.getAvailableCourier();
    }

    @PatchMapping("/{id}/busy")
    public void markAsBusy(@PathVariable Long id) {
        courierService.markAsBusy(id);
    }

    @PatchMapping("/{id}/free")
    public void markAsFree(@PathVariable Long id) {
        courierService.markAsFree(id);
    }
}
