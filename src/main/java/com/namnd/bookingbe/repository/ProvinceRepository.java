package com.namnd.bookingbe.repository;


import com.namnd.bookingbe.model.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Provinces, String> {
}
