package ru.samfort.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;
import ru.samfort.model.User;
import ru.samfort.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.samfort.TestData.*;

class AdminServiceTest extends AbstractServiceTest {

    @Autowired
    AdminServiceImpl adminServiceImpl;

    @Test
    void getUserById() {
        User user = adminServiceImpl.getUserById(USER.getId());
        assertEquals(user, USER);
    }

    @Test
    void getUserByWrongId() {
        assertThrows(NotFoundException.class, ()-> adminServiceImpl.getUserById(1));
    }

    @Test
    void getRestaurantById() {
        Restaurant restaurant = adminServiceImpl.getRestaurantById(R1.getId());
        assertEquals(restaurant, R1);
    }

    @Test
    void getRestaurantByWrongId() {
        assertThrows(NotFoundException.class, ()-> adminServiceImpl.getRestaurantById(1));
    }

    @Test
    void getMenuById() {
        Menu menu = adminServiceImpl.getMenuById(M1.getId());
        assertEquals(menu, M1);
    }

    @Test
    void getMenuByWrongId() {
        assertThrows(NotFoundException.class, ()-> adminServiceImpl.getMenuById(1));
    }

    @Test
    void getAllRestaurantsByAdminId() {
        List <Restaurant> restaurants = adminServiceImpl.getAllRestaurants(ADMIN_1.getId());
        assertEquals(restaurants, ADMIN1_RESTAURANTS);
    }

    @Test
    void getAllRestaurantsByWrongId(){
        List <Restaurant> restaurants = adminServiceImpl.getAllRestaurants(1);
        assertTrue(restaurants.size()==0);
    }

    @Test
    void createRestaurant() {
        Restaurant restaurant = adminServiceImpl.createRestaurant(new Restaurant("TestRestaurant", "TestAddress", ADMIN_1));
        assertEquals(restaurant, CREATED_RESTAURANT);
    }

    @Test
    void deleteRestaurant() {
    }

    @Test
    void updateRestaurant() {
    }

    @Test
    void getAllMenus() {
    }

    @Test
    void createMenu() {
    }

    @Test
    void deleteMenu() {
    }

    @Test
    void updateMenu() {
    }

    @Test
    void getAllDishes() {
    }

    @Test
    void createDish() {
    }

    @Test
    void deleteDish() {
    }

    @Test
    void updateDish() {
    }
}