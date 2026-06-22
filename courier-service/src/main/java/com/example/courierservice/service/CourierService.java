package com.example.courierservice.service;

import com.example.courierservice.dao.entity.CourierEntity;
import com.example.courierservice.dao.repository.CourierRepository;
import com.example.courierservice.dto.CourierRequestDto;
import com.example.courierservice.dto.CourierResponseDto;
import com.example.courierservice.enums.CourierStatus;
import com.example.courierservice.exception.CourierAlreadyExistsException;
import com.example.courierservice.exception.CourierNotFoundException;
import com.example.courierservice.mapper.CourierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourierService {
    private final CourierRepository courierRepository;

    public void createCourier(CourierRequestDto dto) {
        checkCourierIfExists(dto.getPhoneNumber());
        var courier = CourierMapper.mapToEntity(dto);
        courier.setStatus(CourierStatus.FREE);
        courierRepository.save(courier);

    }

    private void checkCourierIfExists(String phoneNumber) {
        if (courierRepository.existsByPhoneNumber(phoneNumber)) {
            throw new CourierAlreadyExistsException("Phone number already exists");
        }
    }

    public List<CourierResponseDto> getAllCouriers() {
        return courierRepository.findAll()
                .stream()
                .map(CourierMapper::mapToResponse)
                .toList();
    }
    public CourierResponseDto getCourierById(Long id){
        var courier = fetchCourierIfExists(id);
        return CourierMapper.mapToResponse(courier);
    }
    public CourierResponseDto getAvailableCourier() {
        var courier = courierRepository.findFirstByStatus(CourierStatus.FREE)
                .orElseThrow(() -> new CourierNotFoundException("No available courier found"));

        return CourierMapper.mapToResponse(courier);
    }
    public CourierEntity fetchCourierIfExists(Long id) {
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("No courier found with id " + id));
    }
    public void markAsBusy(Long id){
        var courier = fetchCourierIfExists(id);
        courier.setStatus(CourierStatus.BUSY);
        courierRepository.save(courier);
    }
    public void markAsFree(Long id){
        var courier = fetchCourierIfExists(id);
        courier.setStatus(CourierStatus.FREE);
        courierRepository.save(courier);
    }

}
