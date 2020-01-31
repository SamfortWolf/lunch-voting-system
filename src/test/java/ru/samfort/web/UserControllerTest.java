package ru.samfort.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;
import ru.samfort.model.Vote;
import ru.samfort.service.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static ru.samfort.TestUtil.*;
import static ru.samfort.TestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest extends AbstractControllerTest{


    public UserControllerTest() {
        super("/rest/vote");
    }

    @Test
    public void getUnAuth () throws Exception {

        mockMvc.perform(get(""))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getAllVotes() throws Exception {
        mockMvc.perform(get("/rest/vote").with(httpBasic("user@yandex.ru","password")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(readListFromJsonMvcResult(result, Vote.class).size()==0));
    }

    @Test
    public void vote() throws Exception {
        VOTE.setId(TEST_SEQ+21);
        mockMvc.perform(post("/rest/vote").param("id","100003").with(httpBasic("user@yandex.ru","password")))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(VOTE, readFromJsonMvcResult(result, Vote.class)));
    }

    @Test
    public void getAllRestaurants() throws Exception {
        mockMvc.perform(get("/rest/vote/restaurants").with(httpBasic("user@yandex.ru","password")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(ALL_RESTAURANTS, readListFromJsonMvcResult(result, Restaurant.class)));
    }

    @Test
    public void getAllMenus() throws Exception {
        mockMvc.perform(get("/rest/vote/menus").param("restaurant_id", "100003").with(httpBasic("user@yandex.ru","password")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(R1_MENUS, readListFromJsonMvcResult(result, Menu.class)));
    }

    @Test
    public void getAllDishes() throws Exception {
        mockMvc.perform(get("/rest/vote/dishes").param("menu_id", "100005").with(httpBasic("user@yandex.ru","password")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(M1_DISHES, readListFromJsonMvcResult(result, Dish.class)));
    }
}
