package ru.samfort.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.samfort.web.LoggedUser;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

//    private static int id = AbstractBaseEntity.START_SEQ+1;

    private SecurityUtil() {
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof LoggedUser) ? (LoggedUser) principal : null;
    }

    public static LoggedUser get () {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }


    public static int getAuthUserId() {
        return get().getId();
    }

//    public static void setAuthUserId(int id) {
//        SecurityUtil.id = id;
//    }

}
