package com.namnd.bookingbe.service;

import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomService {

    ResponseApi<Room> saveRoom(MultipartFile[] files, RoomDTO roomDTO) throws IOException;
    ResponseApi<RoomDTO> findById(Long roomId);
    ResponseApi<List<RoomDTO>> findAllById(Long roomId);
    ResponseApi<List<RoomDTO>> findAll();

    ResponseApi<Void> deleteRoomById(Long id);
}
