package com.namnd.bookingbe.dto.mapper;

import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.model.Room;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RoomMapper {

    public Room toEntity(RoomDTO dto) {
        if (dto == null) {
            return null;
        }

        Room entity = new Room();

        BeanUtils.copyProperties(dto, entity);
        if(StringUtils.hasText(dto.getId())){
            entity.setId(Long.parseLong(dto.getId()));
        }

        if(StringUtils.hasText(dto.getPricePerNight())){
            entity.setPricePerNight(Utils.stringToBigDecimal(dto.getPricePerNight()));
        }

        if(StringUtils.hasText(dto.getCapacity())){
            entity.setCapacity(Integer.parseInt(dto.getCapacity()));
        }
        return entity;
    }

    public RoomDTO toDTO(Room entity) {
        if (entity == null) {
            return null;
        }

        RoomDTO dto = new RoomDTO();

        BeanUtils.copyProperties(entity, dto);

        if (entity.getId() != null) {
            dto.setId(String.valueOf(entity.getId()));
        }

        if (entity.getPricePerNight() != null) {
            dto.setPricePerNight(Utils.bigDecimalToString(entity.getPricePerNight()));
        }

        if (entity.getCapacity() != 0) {
            dto.setCapacity(String.valueOf(entity.getCapacity()));
        }

        return dto;
    }
}
