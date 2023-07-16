package com.namnd.bookingbe.controller;

import com.namnd.bookingbe.dto.CommentDTO;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
