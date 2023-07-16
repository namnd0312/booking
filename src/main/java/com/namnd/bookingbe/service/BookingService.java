package com.namnd.bookingbe.service;

import com.namnd.bookingbe.dto.BookingDTO;
import com.namnd.bookingbe.model.Booking;

public interface BookingService {

    BookingDTO findById(String bookingId);

    void deleteBooking(String bookingId);

    Booking saveBooking(BookingDTO bookingDTO);
}
