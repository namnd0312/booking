package com.namnd.bookingbe.service.impl;

import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.model.Wards;
import com.namnd.bookingbe.repository.WardRepository;
import com.namnd.bookingbe.service.WardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;

    public WardServiceImpl(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    @Override
    public ResponseApi<List<Wards>> findAllByDistrictId(String id) {

        List<Wards> wards = this.wardRepository.findAllByDistrictCode(id);
        return new ResponseApi<>().ok(wards);
    }
}
