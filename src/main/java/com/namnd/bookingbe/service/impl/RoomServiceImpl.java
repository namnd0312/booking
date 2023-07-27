package com.namnd.bookingbe.service.impl;

import com.namnd.bookingbe.config.custom.LogicException;
import com.namnd.bookingbe.dto.PageDataResDTO;
import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.dto.SearchRoomsDTO;
import com.namnd.bookingbe.dto.mapper.RoomMapper;
import com.namnd.bookingbe.model.Room;
import com.namnd.bookingbe.repository.RoomRepository;
import com.namnd.bookingbe.service.FirebaseStorageService;
import com.namnd.bookingbe.service.RoomService;
import com.namnd.bookingbe.utils.Utils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.namnd.bookingbe.Enum.ErrorCode.NOT_FOUND;
import static com.namnd.bookingbe.utils.Utils.stringToBigDecimal;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final FirebaseStorageService firebaseStorageService;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapper roomMapper, FirebaseStorageService firebaseStorageService) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
        this.firebaseStorageService = firebaseStorageService;
    }


    @Override
    public ResponseApi<Room> saveRoom(MultipartFile[] files, RoomDTO roomDTO) throws IOException {
        if (StringUtils.hasText(roomDTO.getId())) {
            Room room = this.roomRepository.findById(Utils.stringToLong(roomDTO.getId())).orElse(null);
            if (room == null) {
                throw new LogicException(NOT_FOUND);
            }
        }


        Room room = this.roomRepository.save(this.roomMapper.toEntity(roomDTO));

        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                firebaseStorageService.uploadFile(files[i]);
            }
            //upload files here


        }
        return new ResponseApi().ok(room);
    }

    @Override
    public ResponseApi<RoomDTO> findById(Long roomId) {

        RoomDTO room = this.roomRepository.findRoomById(roomId).orElse(null);

        if (room == null) {
            throw new LogicException(NOT_FOUND);
        }

        return new ResponseApi().ok(room);
    }

    @Override
    public ResponseApi<List<RoomDTO>> findAllById(Long roomId) {
        Iterable<Room> rooms = this.roomRepository.findAllById(Collections.singleton(roomId));
        List<RoomDTO> results = new ArrayList<>();
        rooms.forEach(room -> results.add(roomMapper.toDTO(room)));

        return new ResponseApi().ok(results);
    }

    @Override
    public ResponseApi<List<RoomDTO>> findAll() {
        Iterable<Room> rooms = roomRepository.findAll();
        List<RoomDTO> results = new ArrayList<>();
        rooms.forEach(room -> results.add(roomMapper.toDTO(room)));

        return new ResponseApi().ok(results);

    }

    @Override
    public ResponseApi<Void> deleteRoomById(Long id) {
        Room room = this.roomRepository.findById(id).orElse(null);

        if (room == null) {
            throw new LogicException(NOT_FOUND);
        }

        this.roomRepository.deleteById(id);

        return new ResponseApi().ok();
    }

    @Override
    public ResponseApi<PageDataResDTO> findAllRoomByCondition(SearchRoomsDTO searchRoomsDTO) {
        Pageable page = PageRequest.of(searchRoomsDTO.getPage(), searchRoomsDTO.getPageSize());
        long totalPage = this.roomRepository.countAllRoomByCondition(searchRoomsDTO.getProvinceCode(), searchRoomsDTO.getDistrictCode(), searchRoomsDTO.getWardCode(), stringToBigDecimal(searchRoomsDTO.getFromPrice()), stringToBigDecimal(searchRoomsDTO.getToPrice()));

        List<RoomDTO> results = roomRepository.findAllRoomByCondition(searchRoomsDTO.getProvinceCode(), searchRoomsDTO.getDistrictCode(), searchRoomsDTO.getWardCode(), stringToBigDecimal(searchRoomsDTO.getFromPrice()), stringToBigDecimal(searchRoomsDTO.getToPrice()), page);

        PageDataResDTO res = PageDataResDTO.builder()
                .results(results)
                .currentPage(page.getPageNumber())
                .currentPageSize(page.getPageSize())
                .totalPage((totalPage + page.getPageSize() - 1) /page.getPageSize())
                .build();

        System.out.println(page.getPageSize());
        return new ResponseApi().ok(res);
    }

}
