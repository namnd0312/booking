package com.namnd.bookingbe.service.impl;

import com.namnd.bookingbe.Enum.ErrorCode;
import com.namnd.bookingbe.config.custom.LogicException;
import com.namnd.bookingbe.dto.CommentDTO;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.mapper.CommentMapper;
import com.namnd.bookingbe.model.Comment;
import com.namnd.bookingbe.repository.CommentRepository;
import com.namnd.bookingbe.service.CommentService;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }


    @Override
    public List<Comment> finAllCommentsByProductId(Long productId) {
        return null;
    }

    @Override
    public Comment saveRoom(CommentDTO commentDTO) {
        return null;
    }

    @Override
    public CommentDTO findById(UUID id) {
        return null;
    }

    @Override
    public List<CommentDTO> findAllById(UUID id) {
        return null;
    }

    @Override
    public List<CommentDTO> findAll() {
        return null;
    }

    @Override
    public void deleteRoomById(UUID id) {

    }

    @Override
    public ResponseApi<CommentDTO> addNewComment(CommentDTO commentDTO) {
        Comment parentComment = null;
        if (StringUtils.hasText(commentDTO.getParentId())) {
            parentComment = commentRepository.findById(UUID.fromString(commentDTO.getParentId()))
                    .orElseThrow(() -> new LogicException(ErrorCode.NOT_FOUND));
        }

        long parentRightKey = parentComment != null ? parentComment.getRightKey() : 0;

        // Dịch chuyển các giá trị bên phải -> tăng 2 nếu có

        if(parentRightKey != 0){
            commentRepository.shiftRightKeys(parentRightKey, 2);
            commentRepository.shiftLeftKeys(parentRightKey, 2);
        }


        // Set left và right cho comment mới
        long newCommentLeftKey = parentRightKey;
        long newCommentRightKey = parentRightKey + 1;
        commentDTO.setLeftKey(Utils.longToString(newCommentLeftKey));
        commentDTO.setRightKey(Utils.longToString(newCommentRightKey));

        Comment newComment = commentRepository.save(commentMapper.toEntity(commentDTO));

        // Cập nhật right key của comment cha
        if (parentComment != null) {
            parentComment.setRightKey(newCommentRightKey + 1);
            commentRepository.save(parentComment);
        }

        return new ResponseApi().ok(newComment);
    }



    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
