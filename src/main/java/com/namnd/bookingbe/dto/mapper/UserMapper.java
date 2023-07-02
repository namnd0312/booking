package com.namnd.bookingbe.dto.mapper;


import com.namnd.bookingbe.dto.UserDTO;
import com.namnd.bookingbe.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserMapper {

    public User toEntity(UserDTO dto){
        User entity = new User();

        BeanUtils.copyProperties(dto, entity);

        if(StringUtils.hasText(dto.getId())){
            entity.setId(Long.parseLong(dto.getId()));
        }
        return entity;
    }

    public UserDTO toDTO(User entity) {
        if (entity == null) {
            return null;
        }

        UserDTO dto = new UserDTO();

        BeanUtils.copyProperties(entity, dto);

        if (entity.getId() != null) {
            dto.setId(String.valueOf(entity.getId()));
        }

        return dto;
    }
}
