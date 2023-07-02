package com.namnd.bookingbe.controller;

import com.namnd.bookingbe.config.custom.RequestProcessor;
import com.namnd.bookingbe.dto.RequestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class ProcessController {

    @Autowired
    private RequestProcessor requestProcessor;

    @PostMapping("/process")
    public ResponseEntity<Object> handleRequest(@Valid @RequestBody RequestApi<Object> request) {
        Object result = requestProcessor.processRequest(request);
        return ResponseEntity.ok(result);
    }
}
