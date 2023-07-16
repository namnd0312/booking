package com.namnd.bookingbe.controller;

import com.namnd.bookingbe.dto.CommentDTO;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.UpdateCommentDTO;
import com.namnd.bookingbe.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseApi<CommentDTO>> addNewComment(@Valid @RequestBody CommentDTO commentDTO) {

        return ResponseEntity.ok(this.commentService.addNewComment(commentDTO));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseApi<Void>> deleteComment(@Valid @PathVariable String commentId) {

        return ResponseEntity.ok(this.commentService.deleteComment(commentId));
    }

    @GetMapping("/parent/{id}")
    public ResponseEntity<?> findByParentId(@PathVariable String id) {
        return ResponseEntity.ok(this.commentService.findAllByParentId(id));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> findAllByProductId(@PathVariable String id) {
        return ResponseEntity.ok(this.commentService.findAllByProductId(id));
    }

    @PostMapping("/update")
    public ResponseEntity<?> addNewComment(@Valid @RequestBody UpdateCommentDTO commentDTO) {
        return ResponseEntity.ok(this.commentService.updateComment(commentDTO));
    }

}
