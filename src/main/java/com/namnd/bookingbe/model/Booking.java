package com.namnd.bookingbe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;


@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant startDate;

    private Instant endDate;

    private String note;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;

}
