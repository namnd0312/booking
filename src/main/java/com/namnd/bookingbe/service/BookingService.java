package com.namnd.bookingbe.service;

import com.namnd.bookingbe.dto.BookingDTO;

public interface BookingService {

    BookingDTO findById(String bookingId);

    void deleteBooking(String bookingId);

    void createBooking(BookingDTO bookingDTO);
    void updateBooking(BookingDTO bookingDTO);
}
