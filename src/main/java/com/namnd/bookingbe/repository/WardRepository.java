package com.namnd.bookingbe.repository;


import com.namnd.bookingbe.model.Wards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Wards, String> {
    List<Wards> findAllByDistrictCode(String districtCode);
}
