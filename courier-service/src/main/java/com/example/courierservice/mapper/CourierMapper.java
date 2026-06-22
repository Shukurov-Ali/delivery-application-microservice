package com.example.courierservice.mapper;

import com.example.courierservice.dao.entity.CourierEntity;
import com.example.courierservice.dto.CourierRequestDto;
import com.example.courierservice.dto.CourierResponseDto;
import com.example.courierservice.enums.CourierStatus;

public class CourierMapper {
    public static CourierEntity mapToEntity(CourierRequestDto dto) {
    var entity = new CourierEntity();
    entity.setFullName(dto.getFullName());
    entity.setPhoneNumber(dto.getPhoneNumber());
    entity.setStatus(CourierStatus.FREE);
    return entity;
    }
    public static CourierResponseDto mapToResponse(CourierEntity entity) {
        var responseDto = new CourierResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setFullName(entity.getFullName());
        responseDto.setPhoneNumber(entity.getPhoneNumber());
        responseDto.setStatus(entity.getStatus());
        return responseDto;

    }
}
