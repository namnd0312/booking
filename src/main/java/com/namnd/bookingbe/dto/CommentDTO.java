package com.namnd.bookingbe.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CommentDTO {

    private String id;

    @NotEmpty
    private String content;

    private String parentId;

    private String leftKey;

    private String rightKey;

    private String timeCreate;

    private String timeUpdate;

}
