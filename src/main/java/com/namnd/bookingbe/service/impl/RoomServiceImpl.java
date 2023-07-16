package com.namnd.bookingbe.service.impl;

import com.namnd.bookingbe.config.custom.LogicException;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.dto.mapper.RoomMapper;
import com.namnd.bookingbe.model.Room;
import com.namnd.bookingbe.repository.RoomRepository;
import com.namnd.bookingbe.service.RoomService;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.namnd.bookingbe.Enum.ErrorCode.NOT_FOUND;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }


    @Override
    public ResponseApi<Room> saveRoom(RoomDTO roomDTO) {
        if (StringUtils.hasText(roomDTO.getId())) {
            Room room = this.roomRepository.findById(Utils.stringToLong(roomDTO.getId())).orElse(null);
            if (room == null) {
                throw new LogicException(NOT_FOUND);
            }
        }


        Room room = this.roomRepository.save(this.roomMapper.toEntity(roomDTO));
        return  new ResponseApi().ok(room);
    }

    @Override
    public ResponseApi<RoomDTO> findById(Long roomId) {

        Room room = this.roomRepository.findById(roomId).orElse(null);

        if (room == null) {
            throw new LogicException(NOT_FOUND);
        }
        RoomDTO result = this.roomRepository.findById(roomId).map(roomMapper::toDTO).get();
        return  new ResponseApi().ok(result);
    }

    @Override
    public ResponseApi<List<RoomDTO>> findAllById(Long roomId) {
        List<RoomDTO> results =  this.roomRepository.findAllById(Collections.singleton(roomId))
                .stream()
                .map(this.roomMapper::toDTO).
                collect(Collectors.toList());

        return  new ResponseApi().ok(results);
    }

    @Override
    public ResponseApi<List<RoomDTO>> findAll() {
        List<RoomDTO> results =  this.roomRepository.findAll()
                .stream()
                .map(this.roomMapper::toDTO).
                collect(Collectors.toList());

        return  new ResponseApi().ok(results);
    }

    @Override
    public ResponseApi<Void> deleteRoomById(Long id) {
        Room room = this.roomRepository.findById(id).orElse(null);

        if (room == null) {
            throw new LogicException(NOT_FOUND);
        }

        this.roomRepository.deleteById(id);

        return  new ResponseApi().ok();
    }
}
