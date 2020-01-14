package ru.samfort.util;

import ru.samfort.model.AbstractBaseEntity;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ+1;

    private SecurityUtil() {
    }

    public static int getAuthUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

}
