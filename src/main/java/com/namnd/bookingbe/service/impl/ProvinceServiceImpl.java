package com.namnd.bookingbe.service.impl;

import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.model.Provinces;
import com.namnd.bookingbe.repository.ProvinceRepository;
import com.namnd.bookingbe.service.ProvinceService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public ResponseApi<List<Provinces>> findAll() {
        List<Provinces> provinces = this.provinceRepository.findAll();
        return new ResponseApi<>().ok(provinces);
    }
}
