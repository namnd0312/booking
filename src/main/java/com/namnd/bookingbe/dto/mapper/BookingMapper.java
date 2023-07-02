package com.namnd.bookingbe.dto.mapper;

import com.namnd.bookingbe.dto.BookingDTO;
import com.namnd.bookingbe.model.Booking;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BookingMapper {

    private final RoomMapper roomMapper;

    private final UserMapper userMapper;


    public BookingMapper(RoomMapper roomMapper, UserMapper userMapper) {
        this.roomMapper = roomMapper;
        this.userMapper = userMapper;
    }

    public Booking toEntity(BookingDTO dto) {
        if (dto == null) {
            return null;
        }

        Booking entity = new Booking();

        BeanUtils.copyProperties(dto, entity);
        if(StringUtils.hasText(dto.getId())){
            entity.setId(Long.parseLong(dto.getId()));
        }

        if(dto.getRoom() != null){
            entity.setRoom(this.roomMapper.toEntity(dto.getRoom()));
        }

        if(dto.getUser() != null){
            entity.setUser(this.userMapper.toEntity(dto.getUser()));
        }
        return entity;
    }

    public BookingDTO toDTO(Booking entity) {
        if (entity == null) {
            return null;
        }

        BookingDTO dto = new BookingDTO();

        BeanUtils.copyProperties(entity, dto);
        if(StringUtils.hasText(dto.getId())){
            dto.setId(Utils.longToString(entity.getId()));
        }

        if(entity.getRoom() != null){
            dto.setRoom(this.roomMapper.toDTO(entity.getRoom()));
        }

        if(entity.getUser() != null){
            dto.setUser(this.userMapper.toDTO(entity.getUser()));
        }
        return dto;
    }
}
