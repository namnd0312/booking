package com.namnd.bookingbe.service.impl;

import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.model.Districts;
import com.namnd.bookingbe.repository.DistrictRepository;
import com.namnd.bookingbe.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public ResponseApi<List<Districts>> findAllByProvinceId(String id) {
        List<Districts> districts = this.districtRepository.findAllByProvinceCodes(id);
        return new ResponseApi<>().ok(districts);
    }
}
