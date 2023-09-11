package com.namnd.bookingbe.model.elasticsearch;

import com.namnd.bookingbe.Enum.RoomStatus;
import com.namnd.bookingbe.Enum.TypeOfRoom;
import com.namnd.bookingbe.model.Comment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.annotation.Id;

@Document(indexName = "room")
@Getter
@Setter
public class RoomDocument {
    @Id
    private Long id;

    private String roomNumber;

    private TypeOfRoom roomType;

//    private int capacity;

    private BigDecimal pricePerNight;

    private RoomStatus status;

    private List<Comment> comments;

    private String address;

    private String provinceCode;


    private String districtCode;

    private String wardCode;
}
