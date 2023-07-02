package com.namnd.bookingbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Builder

public class RequestApi <T>{

    @NotEmpty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestName;

    @Valid
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T body;

    public RequestApi() {
    }

    public RequestApi(String requestName, T body) {
        this.requestName = requestName;
        this.body = body;
    }
}
