package com.namnd.bookingbe.service;

import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.model.Wards;

import java.util.List;

public interface WardService {
    ResponseApi<List<Wards>>  findAllByDistrictId(String id);
}
