package com.namnd.bookingbe.service;

import com.namnd.bookingbe.dto.ResponseApi;
import com.namnd.bookingbe.model.Provinces;

import java.util.List;

public interface ProvinceService {

    ResponseApi<List<Provinces>> findAll();
}
