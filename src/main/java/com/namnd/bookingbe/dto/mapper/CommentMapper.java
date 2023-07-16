package com.namnd.bookingbe.dto.mapper;


import com.namnd.bookingbe.dto.CommentDTO;
import com.namnd.bookingbe.dto.UpdateCommentDTO;
import com.namnd.bookingbe.model.Comment;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static com.namnd.bookingbe.utils.Utils.instantToString;

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


    public Comment toEntity(Comment entity, UpdateCommentDTO dto) {
        if (dto == null) {
            return null;
        }

        BeanUtils.copyProperties(dto, entity);

        return entity;
    }

    public CommentDTO toDTO(Comment entity) {
        if (entity == null) {
            return null;
        }

        CommentDTO dto = new CommentDTO();

        BeanUtils.copyProperties(entity, dto);

        if(entity.getId() != null){
            dto.setId(entity.getId().toString());
        }

        if(entity.getLeftKey() != null){
            dto.setLeftKey(Utils.longToString(entity.getLeftKey()));
        }

        if(entity.getRightKey() != null){
            dto.setRightKey(Utils.longToString(entity.getRightKey()));
        }

        if(entity.getTimeCreate() != null){
            dto.setTimeCreate(instantToString(entity.getTimeCreate()));
        }

        if(entity.getTimeUpdate() != null){
            dto.setTimeUpdate(instantToString(entity.getTimeUpdate()));
        }

        return dto;
    }
}
