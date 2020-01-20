package ru.samfort.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import ru.samfort.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends AbstractControllerTest{

    @Autowired
    private UserService userService;

    public UserControllerTest() {
        super("/rest/vote");
    }

    @Test
    public void getUnAuth () throws Exception {
        mockMvc.perform(get(""))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getAllVotes() {

    }

    @Test
    public void vote() {
    }

    @Test
    public void getAllRestaurants() throws Exception {

    }

    @Test
    public void getAllMenus() {
    }

    @Test
    public void getAllDishes() {
    }
}
