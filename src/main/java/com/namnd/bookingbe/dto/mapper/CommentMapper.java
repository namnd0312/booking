package com.namnd.bookingbe.dto.mapper;


import com.namnd.bookingbe.dto.CommentDTO;
import com.namnd.bookingbe.model.Comment;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
public class CommentMapper {

    public Comment toEntity(CommentDTO dto) {
        if (dto == null) {
            return null;
        }

        Comment entity = new Comment();

        BeanUtils.copyProperties(dto, entity);

        if(StringUtils.hasText(dto.getId())){
            entity.setId(UUID.fromString(dto.getId()));
        }

        if(dto.getLeftKey() != null){
            entity.setLeftKey(Utils.stringToLong(dto.getLeftKey()));
        }

        if(dto.getRightKey() != null){
            entity.setRightKey(Utils.stringToLong(dto.getRightKey()));
        }

        return entity;
    }

    public CommentDTO toDTO(Comment entity) {
        if (entity == null) {
            return null;
        }

        CommentDTO dto = new CommentDTO();

        BeanUtils.copyProperties(entity, dto);
        if(dto.getId() != null){
            dto.setId(entity.getId().toString());
        }

        if(entity.getLeftKey() != null){
            dto.setLeftKey(Utils.longToString(entity.getLeftKey()));
        }

        if(entity.getRightKey() != null){
            dto.setRightKey(Utils.longToString(entity.getRightKey()));
        }

        return dto;
    }
}
