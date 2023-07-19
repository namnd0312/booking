package com.namnd.bookingbe.controller;


import com.namnd.bookingbe.service.ProvinceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/province")
public class ProvinceController {
    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> fetchAllProvinces() {
        return ResponseEntity.ok(this.provinceService.findAll());
    }
}
