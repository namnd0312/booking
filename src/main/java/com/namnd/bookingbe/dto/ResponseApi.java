package com.namnd.bookingbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.namnd.bookingbe.Enum.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ResponseApi <T>{

    private String status;

    private String code;

    private String message;

    private T data;



    public  ResponseApi ok(T data){
        return   ResponseApi.builder()
                .code("00")
                .status("OK")
                .data(data)
                .build();
    }

    public  ResponseApi ok(){
        return  ResponseApi.builder()
                .code("00")
                .status("OK")
                .build();
    }

    public ResponseApi error(ErrorCode errorCode){
        return  ResponseApi.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status("FAIL")
                .build();
    }

    public ResponseApi error(String code, String  message){
        return  ResponseApi.builder()
                .code(code)
                .message(message)
                .status("FAIL")
                .build();
    }

    public ResponseApi() {
    }


}
