package com.namnd.bookingbe.service.impl;


import com.namnd.bookingbe.config.custom.LogicException;
import com.namnd.bookingbe.dto.BookingDTO;
import com.namnd.bookingbe.dto.mapper.BookingMapper;
import com.namnd.bookingbe.model.Booking;
import com.namnd.bookingbe.repository.BookingRepository;
import com.namnd.bookingbe.service.BookingService;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static com.namnd.bookingbe.Enum.ErrorCode.NOT_FOUND;

@Service
@Transactional
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
                .map(bookingMapper::toDTO)
                .orElseThrow(() -> new LogicException(NOT_FOUND));

    }

    @Override
    public void deleteBooking(String bookingId) {
        this.bookingRepository.deleteById(Utils.stringToLong(bookingId));

    }

    @Override
    public Booking saveBooking(BookingDTO bookingDTO) {

        if(StringUtils.hasText(bookingDTO.getId())){
            Booking booking = this.bookingRepository.findById(Long.parseLong(bookingDTO.getId())).orElse(null);

            if(booking == null){
                throw  new LogicException(NOT_FOUND);
            }
        }

        Booking entity = this.bookingMapper.toEntity(bookingDTO);

        return this.bookingRepository.save(entity);
    }

}
