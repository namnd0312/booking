package com.namnd.bookingbe.config.custom;

import com.namnd.bookingbe.dto.RequestApi;

public interface RequestHandler {

    String processRequest(RequestApi<?> request);
}
