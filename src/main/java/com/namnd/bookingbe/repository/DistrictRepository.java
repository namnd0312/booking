package com.namnd.bookingbe.repository;

import com.namnd.bookingbe.model.Districts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<Districts, String> {
    List<Districts> findAllByProvinceCodes(String provinceCode);
}
