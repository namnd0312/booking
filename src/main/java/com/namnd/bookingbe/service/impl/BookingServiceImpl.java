package com.namnd.bookingbe.service.impl;


import com.namnd.bookingbe.config.custom.LogicException;
import com.namnd.bookingbe.dto.BookingDTO;
import com.namnd.bookingbe.dto.mapper.BookingMapper;
import com.namnd.bookingbe.repository.BookingRepository;
import com.namnd.bookingbe.service.BookingService;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.namnd.bookingbe.Enum.ErrorCode.NOT_FOUND;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }


    @Transactional(readOnly = true)
    @Override
    public BookingDTO findById(String bookingId) {
        return this.bookingRepository.findById(Long.parseLong(bookingId))
                .map(bookingMapper::toDTO) .orElseThrow(() -> new LogicException(NOT_FOUND));

    }

    @Override
    public void deleteBooking(String bookingId) {
        this.bookingRepository.deleteById(Utils.stringToLong(bookingId));

    }

    @Override
    public void createBooking(BookingDTO bookingDTO) {

    }

    @Override
    public void updateBooking(BookingDTO bookingDTO) {

    }
}
