package com.namnd.bookingbe.model;


import com.namnd.bookingbe.Enum.TypeOfRoom;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    private TypeOfRoom roomType;

    private int capacity;

    private BigDecimal pricePerNight;

}
