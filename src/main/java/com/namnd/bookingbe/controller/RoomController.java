package com.namnd.bookingbe.controller;


import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.model.Room;
import com.namnd.bookingbe.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseApi<Room>> saveRoom(@Valid @RequestBody RoomDTO roomDTO) {

        return ResponseEntity.ok(this.roomService.saveRoom(roomDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoomById(@Valid @PathVariable Long id) {
        this.roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findRoomById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(this.roomService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllRoom() {
        return new ResponseEntity<>(this.roomService.findAll(), HttpStatus.OK);
    }
}
