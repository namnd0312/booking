package com.namnd.bookingbe.service.impl;

import com.namnd.bookingbe.Enum.ErrorCode;
import com.namnd.bookingbe.config.custom.LogicException;
import com.namnd.bookingbe.dto.CommentDTO;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.UpdateCommentDTO;
import com.namnd.bookingbe.dto.mapper.CommentMapper;
import com.namnd.bookingbe.model.Comment;
import com.namnd.bookingbe.repository.CommentRepository;
import com.namnd.bookingbe.service.CommentService;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public ResponseApi<Comment> updateComment(UpdateCommentDTO commentDTO) {
        Comment entityFound = commentRepository.findById(UUID.fromString(commentDTO.getId()))
                .orElseThrow(() -> new LogicException(ErrorCode.NOT_FOUND));


        Comment newEntityMapper = this.commentMapper.toEntity(entityFound, commentDTO);
        newEntityMapper.setTimeUpdate(Instant.now());
        Comment newEntity = this.commentRepository.save(newEntityMapper);
        return new ResponseApi().ok(newEntity);
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

        Comment newEntity = commentMapper.toEntity(commentDTO);
        newEntity.setTimeCreate(Instant.now());
        Comment newComment = commentRepository.save(newEntity);

        // Cập nhật right key của comment cha
        if (parentComment != null) {
            parentComment.setRightKey(newCommentRightKey + 1);
            commentRepository.save(parentComment);
        }

        return new ResponseApi().ok(newComment);
    }

    @Override
    public ResponseApi<Void> deleteComment(String commentId) {
            Comment comment = commentRepository.findById(UUID.fromString(commentId))
                    .orElseThrow(() -> new LogicException(ErrorCode.NOT_FOUND));

        long leftKey = comment.getLeftKey();
        long rightKey = comment.getRightKey();
        long shiftBy = (rightKey - leftKey + 1);

        commentRepository.deleteNode(leftKey, rightKey);

        commentRepository.shiftLeftKeysAbstract(leftKey, shiftBy);
        commentRepository.shiftRightKeysAbstract(rightKey, shiftBy);

        return new ResponseApi().ok();
    }

    @Override
    public ResponseApi<List<CommentDTO>> findAllByProductId(String id) {

        List<CommentDTO> commentDTOS = this.commentRepository.findAllByRoomIdAndParentIdIsNull(Utils.stringToLong(id))
                .stream()
                .map(this.commentMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseApi().ok(commentDTOS);
    }

    @Override
    public ResponseApi<List<CommentDTO>>  findAllByParentId(String parentId) {
        List<CommentDTO> commentDTOS = this.commentRepository.findAllByParentId(parentId)
                .stream()
                .map(this.commentMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseApi().ok(commentDTOS);
    }

}
