package com.namnd.bookingbe.dto;


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
