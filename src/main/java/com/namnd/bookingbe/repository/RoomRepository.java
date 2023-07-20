package com.namnd.bookingbe.repository;

import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select new com.namnd.bookingbe.dto.RoomDTO(concat('', b.id), b.roomNumber, b.roomType, concat('', b.pricePerNight), b.status, b.address, concat(w.fullName, ', ', d.fullName, ', ', p.fullName), b.provinceCode, b.districtCode, b.wardCode)" +
            "from Room b " +
            "left join Provinces p on b.provinceCode = p.code " +
            "left join Districts d on b.districtCode = d.code " +
            "left join Wards w on b.wardCode= w.code " +
            "where b.id = ?1")
    Optional<RoomDTO> findRoomById(Long id);
}
