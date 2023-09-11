package com.namnd.bookingbe.controller;


import com.namnd.bookingbe.dto.BaseSearchDTO;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.dto.SearchRoomsDTO;
import com.namnd.bookingbe.model.Room;
import com.namnd.bookingbe.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseApi<Room>> saveRoom(@RequestPart("files") MultipartFile[] files, @Valid @RequestPart("roomDTO") RoomDTO roomDTO) throws IOException {

        return ResponseEntity.ok(this.roomService.saveRoom(files, roomDTO));
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

    @PostMapping("/get-all-by-condition")
    public ResponseEntity<?> findAllRoomByCondition(@RequestBody @Valid  SearchRoomsDTO searchRoomsDTO) {
        return new ResponseEntity<>(this.roomService.findAllRoomByCondition(searchRoomsDTO), HttpStatus.OK);
    }

}
