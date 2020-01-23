package ru.samfort.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;
import ru.samfort.model.Vote;
import ru.samfort.web.LoggedUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.samfort.TestData.*;

@Transactional
class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getAll() {
        Vote vote = userService.vote(100003, false, USER.getId());
        List<Vote> votes = userService.getAll(USER.getId());
        VOTE.setId(vote.getId());
        assertEquals(votes, List.of(VOTE));
    }

    @Test
    void newVote() {
        Vote testVote = userService.vote(100004, false, USER.getId());
        assertEquals(List.of(testVote), userService.getAll(USER.getId()));
    }

    @Test
    void updateVote() {
        Vote testVote = userService.vote(100004, false, USER.getId());
        Vote updated = userService.vote(100003, false, USER.getId());
        testVote.setRestaurant(R1);
        assertEquals(testVote.getRestaurant(), updated.getRestaurant());
    }

    @Test
    void expireVote() {
        Vote testVote = userService.vote(100004, false, USER.getId());
        Vote updated = userService.vote(100003, true, USER.getId());
        assertEquals(testVote, updated);
    }

    @Test
    void getAllRestaurants() {
        List<Restaurant> restaurants = userService.getAllRestaurants();
        assertEquals(restaurants, ALL_RESTAURANTS);
    }

    @Test
    void getAllMenus() {
        List<Menu> menus = userService.getAllMenus();
        assertEquals(menus, ALL_MENUS);
    }

    @Test
    void getMenusByRestaurantId() {
        List<Menu> menus = userService.getMenusByRestaurantId(R1.getId());
        assertEquals(menus, R1_MENUS);
    }

    @Test
    void getMenusByRestaurantIdNotFound() {
        List<Menu> menus = userService.getMenusByRestaurantId(1);
        assertTrue(menus.size() == 0);
    }

    @Test
    void getAllDishes() {
        List<Dish> dishes = userService.getAllDishes();
        assertEquals(dishes, ALL_DISHES);
    }

    @Test
    void getDishesByMenuId() {
        List<Dish> dishes = userService.getDishesByMenuId(M1.getId());
        assertEquals(dishes, M1_DISHES);
    }

    @Test
    void getDishesByMenuIdNotFound() {
        List<Dish> dishes = userService.getDishesByMenuId(1);
        assertTrue(dishes.size() == 0);
    }

    @Test
    void loadUserByUsername() {
        LoggedUser loggedUser = (LoggedUser) userService.loadUserByUsername("user@yandex.ru");
        assertEquals(loggedUser.getUser(), USER);
    }

    @Test
    void loadUserByUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(""));
    }
}