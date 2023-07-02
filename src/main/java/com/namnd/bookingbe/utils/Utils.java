package com.namnd.bookingbe.utils;


import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Utils {

    public static String bigDecimalToString(BigDecimal number) {
        return number.toString();
    }

    public static BigDecimal stringToBigDecimal(String str) {
        return new BigDecimal(str);
    }

    public static String longToString(long number) {
        return String.valueOf(number);
    }

    public static long stringToLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ nếu chuỗi không thể chuyển đổi thành long
            throw new IllegalArgumentException("Invalid long value: " + str);
        }
    }
}
