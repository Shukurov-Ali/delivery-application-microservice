package com.example.orderservice.client;

import com.example.orderservice.dto.CourierResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "courier-service",
        url = "http://localhost:8080"
)
public interface CourierClient {

    @GetMapping("/couriers/available")
    CourierResponseDto getAvailableCouriers();

    @PostMapping("/couriers/{id}/busy")
    void markCourierAsBusy(@PathVariable Long id);

    @PostMapping("/couriers/{id}/free")
    void markCourierAsFree(@PathVariable Long id);
}
