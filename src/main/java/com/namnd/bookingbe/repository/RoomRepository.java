package com.namnd.bookingbe.repository;

import com.namnd.bookingbe.dto.RoomDTO;
import com.namnd.bookingbe.model.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    @Query("select new com.namnd.bookingbe.dto.RoomDTO(concat('', b.id), b.roomNumber, b.roomType, concat('', b.pricePerNight), b.status, b.address, concat(w.fullName, ', ', d.fullName, ', ', p.fullName), b.provinceCode, b.districtCode, b.wardCode)" +
            "from Room b " +
            "left join Provinces p on b.provinceCode = p.code " +
            "left join Districts d on b.districtCode = d.code " +
            "left join Wards w on b.wardCode= w.code " +
            "where b.id = ?1")
    Optional<RoomDTO> findRoomById(Long id);

    @Query("select new com.namnd.bookingbe.dto.RoomDTO(concat('', b.id), b.roomType, concat('', b.pricePerNight), b.status)" +
            "from Room b " +
            "WHERE (:provinceCode IS NULL OR b.provinceCode = :provinceCode) " +
            "AND (:districtCode IS NULL OR b.districtCode = :districtCode) " +
            "AND (:wardCode IS NULL OR b.wardCode = :wardCode) " +
            "AND (:fromPrice IS NULL OR b.pricePerNight >= :fromPrice) " +
            "AND (:toPrice IS NULL OR b.pricePerNight <= :toPrice)")
    List<RoomDTO> findAllRoomByCondition(@Param("provinceCode") String provinceCode,
                                         @Param("districtCode") String districtCode,
                                         @Param("wardCode") String wardCode,
                                         @Param("fromPrice") BigDecimal fromPrice,
                                         @Param("toPrice") BigDecimal toPrice,
                                         Pageable pageable);

    @Query("SELECT COUNT(b.id) FROM Room b " +
            "WHERE (:provinceCode IS NULL OR b.provinceCode = :provinceCode) " +
            "AND (:districtCode IS NULL OR b.districtCode = :districtCode) " +
            "AND (:wardCode IS NULL OR b.wardCode = :wardCode) " +
            "AND (:fromPrice IS NULL OR b.pricePerNight >= :fromPrice) " +
            "AND (:toPrice IS NULL OR b.pricePerNight <= :toPrice)")
    long countAllRoomByCondition(@Param("provinceCode") String provinceCode,
                                 @Param("districtCode") String districtCode,
                                 @Param("wardCode") String wardCode,
                                 @Param("fromPrice") BigDecimal fromPrice,
                                 @Param("toPrice") BigDecimal toPrice);
}
