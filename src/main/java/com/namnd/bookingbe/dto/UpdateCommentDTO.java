package com.namnd.bookingbe.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdateCommentDTO {

    @NotEmpty
    private String id;

    @NotEmpty
    private String content;
}
