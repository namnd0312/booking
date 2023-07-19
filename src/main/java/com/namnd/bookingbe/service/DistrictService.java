package com.namnd.bookingbe.service;

import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.model.Districts;

import java.util.List;

public interface DistrictService {

    ResponseApi<List<Districts>> findAllByProvinceId(String id);
}
