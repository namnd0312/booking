package com.namnd.bookingbe.utils;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {

    public static String bigDecimalToString(BigDecimal number) {
        return number.toString();
    }

    public static BigDecimal stringToBigDecimal(String str) {

        if(!StringUtils.hasText(str)){
            return null;
        }

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

    public static String instantToString(Instant instant) {
//        Instant instant = Instant.now();

        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(zonedDateTime);
    }
}
