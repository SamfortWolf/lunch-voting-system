package ru.samfort.voting.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ValidationUtil {
    private final static LocalTime expireTime = LocalTime.of(11,00);

    public static boolean isTimeExpire (LocalDateTime ldt){
        return ldt.toLocalTime().isAfter(expireTime);
    }
}
