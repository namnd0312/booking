package com.namnd.bookingbe.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Getter
@Setter
@Service
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "content", length = 5000, nullable = false)
    private String content;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "left_key")
    private Long leftKey;

    @Column(name = "right_key")
    private Long rightKey;

    @OneToOne
    private Room room;

}
