package com.namnd.bookingbe.dto;

import com.namnd.bookingbe.Enum.RoomStatus;
import com.namnd.bookingbe.Enum.TypeOfRoom;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class RoomDTO {

    private String id;

    @NotEmpty
    private String roomNumber;

    @NotNull
    private TypeOfRoom roomType;

    @NotEmpty
    private String pricePerNight;

    @NotNull
    private RoomStatus status;

    private List<CommentDTO> comments;
}
