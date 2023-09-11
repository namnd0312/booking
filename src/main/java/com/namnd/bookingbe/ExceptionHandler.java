package com.namnd.bookingbe;


import com.namnd.bookingbe.dto.ResponseApi;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.namnd.bookingbe.Enum.ErrorCode.INVALID_INPUT;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseApi<?> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors().forEach(i -> {
                    errors.put(i.getField(), i.getDefaultMessage());
                });


        StringBuilder errorMessage = new StringBuilder();
        errors.forEach((key, value) -> errorMessage.append(key).append(": ").append(value).append("#"));


        return  new ResponseApi().error(INVALID_INPUT.getCode(), errorMessage.toString());
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
//    @ResponseBody
//    public Map<String, List<String>> handleCustomValidationException(ValidationException ex) {
//        return Collections.singletonMap("errors", ex.getMessage());
//    }
}
