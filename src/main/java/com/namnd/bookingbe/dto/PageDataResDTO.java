package com.namnd.bookingbe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class PageDataResDTO {

    private Object results;

    private int currentPage;

    private int currentPageSize;

    private long totalPage;
}
