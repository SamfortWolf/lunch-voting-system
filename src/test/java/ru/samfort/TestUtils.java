package ru.samfort;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.samfort.model.User;
import ru.samfort.web.LoggedUser;

public class TestUtils {

    public static void mockAuthorize(User user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(new LoggedUser(user), null, user.getRoles()));
    }
}
