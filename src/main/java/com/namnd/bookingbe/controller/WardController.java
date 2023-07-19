package com.namnd.bookingbe.controller;


import com.namnd.bookingbe.service.WardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ward")
public class WardController {

    private final WardService wardService;

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping("/get-all-by-district-id/{id}")
    public ResponseEntity<?> findAllByDistrict(@PathVariable String id) {
        return ResponseEntity.ok(this.wardService.findAllByDistrictId(id));
    }
}
