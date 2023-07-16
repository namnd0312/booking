package com.namnd.bookingbe.service;

import com.namnd.bookingbe.dto.CommentDTO;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.UpdateCommentDTO;
import com.namnd.bookingbe.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {


    ResponseApi<Comment> updateComment(UpdateCommentDTO commentDTO);
    ResponseApi<CommentDTO> addNewComment(CommentDTO commentDTO);
    ResponseApi<Void> deleteComment(String commentId);

    ResponseApi<List<CommentDTO>> findAllByProductId(String id);

    ResponseApi<List<CommentDTO>>  findAllByParentId(String parentId);
}
