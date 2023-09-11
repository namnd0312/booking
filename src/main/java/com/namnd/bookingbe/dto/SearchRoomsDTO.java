package com.namnd.bookingbe.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SearchRoomsDTO extends BaseSearchDTO{

    @NotEmpty
    @NotNull
    private String provinceCode;

    @NotEmpty
    private String districtCode;

    private String wardCode;

    private String fromPrice;

    private String toPrice;
}
