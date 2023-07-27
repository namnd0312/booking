package com.namnd.bookingbe.dto;

import com.namnd.bookingbe.Enum.RoomStatus;
import com.namnd.bookingbe.Enum.TypeOfRoom;
import com.namnd.bookingbe.model.Districts;
import com.namnd.bookingbe.model.Provinces;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private String address;

    private String fullAddress;

    private String provinceCode;

    private String districtCode;

    private String wardCode;



    public RoomDTO() {
    }


    public RoomDTO(String id, TypeOfRoom roomType, String pricePerNight, RoomStatus status) {
        this.id = id;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    public RoomDTO(String id, String roomNumber, TypeOfRoom roomType, String pricePerNight, RoomStatus status, String address, String fullAddress) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.address = address;
        this.fullAddress = fullAddress;
    }

    public RoomDTO(String id, String roomNumber, TypeOfRoom roomType, String pricePerNight, RoomStatus status, String address, String fullAddress, String provinceCode, String districtCode, String wardCode) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.address = address;
        this.fullAddress = fullAddress;
        this.provinceCode = provinceCode;
        this.districtCode = districtCode;
        this.wardCode = wardCode;
    }
}
