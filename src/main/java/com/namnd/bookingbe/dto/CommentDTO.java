package com.namnd.bookingbe.dto;


import com.namnd.bookingbe.model.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private String id;

    private String content;

    private String parentId;

    private String leftKey;

    private String rightKey;

    private Room room;
}
