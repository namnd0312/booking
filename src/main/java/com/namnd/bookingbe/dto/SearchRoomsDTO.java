package com.namnd.bookingbe.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRoomsDTO extends BaseSearchDTO{

    private String provinceCode;

    private String districtCode;

    private String wardCode;

    private String fromPrice;

    private String toPrice;
}
