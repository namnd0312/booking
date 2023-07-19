package com.namnd.bookingbe.controller;

import com.namnd.bookingbe.service.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/district")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/get-all-by-province-id/{id}")
    public ResponseEntity<?> findAllByProvince(@PathVariable String id) {
        return ResponseEntity.ok(this.districtService.findAllByProvinceId(id));
    }
}
