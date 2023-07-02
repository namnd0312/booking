package com.namnd.bookingbe.dto;


import com.namnd.bookingbe.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {

    private String id;

    private String startDate;

    private String endDate;

    private String note;


    private RoomDTO room;


    private UserDTO user;

}
