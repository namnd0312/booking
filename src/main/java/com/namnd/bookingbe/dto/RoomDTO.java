package com.namnd.bookingbe.dto;

import com.namnd.bookingbe.Enum.TypeOfRoom;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
public class RoomDTO {

    private String id;

    private String roomNumber;

    private TypeOfRoom roomType;

    private String capacity;

    private String pricePerNight;
}
