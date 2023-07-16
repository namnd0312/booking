package com.namnd.bookingbe.service;

import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.model.Room;

import java.util.List;

public interface RoomService {

    ResponseApi<Room> saveRoom(RoomDTO roomDTO);
    ResponseApi<RoomDTO> findById(Long roomId);
    ResponseApi<List<RoomDTO>> findAllById(Long roomId);
    ResponseApi<List<RoomDTO>> findAll();

    ResponseApi<Void> deleteRoomById(Long id);
}
