package com.namnd.bookingbe.service;

import com.namnd.bookingbe.dto.CommentDTO;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.model.Comment;
import com.namnd.bookingbe.model.Room;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<Comment> finAllCommentsByProductId(Long productId);

    Comment saveRoom(CommentDTO commentDTO);
    CommentDTO findById(UUID id);
    List<CommentDTO> findAllById(UUID id);
    List<CommentDTO> findAll();

    void deleteRoomById(UUID id);

    ResponseApi<CommentDTO> addNewComment(CommentDTO commentDTO);
}
