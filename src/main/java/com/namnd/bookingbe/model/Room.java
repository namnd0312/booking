package com.namnd.bookingbe.model;


import com.namnd.bookingbe.Enum.RoomStatus;
import com.namnd.bookingbe.Enum.TypeOfRoom;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    private TypeOfRoom roomType;

//    private int capacity;

    private BigDecimal pricePerNight;

    private RoomStatus status;

    @OneToMany
    private List<Comment> comments;
}
