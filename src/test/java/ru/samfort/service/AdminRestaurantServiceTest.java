package ru.samfort.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.samfort.model.Restaurant;
import ru.samfort.model.User;
import ru.samfort.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.samfort.TestData.*;
import static ru.samfort.TestData.CREATED_RESTAURANT;

class AdminRestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    AdminRestaurantService service;

    @Test
    void getUserById() {
        User user = service.getUserById(USER.getId());
        assertEquals(user, USER);
    }

    @Test
    void getUserByWrongId() {
        assertThrows(NotFoundException.class, ()-> service.getUserById(1));
    }

    @Test
    void getRestaurantById() {
        Restaurant restaurant = service.getRestaurantById(R1.getId());
        assertEquals(restaurant, R1);
    }

    @Test
    void getRestaurantByWrongId() {
        assertThrows(NotFoundException.class, ()-> service.getRestaurantById(1));
    }

    @Test
    void getAllRestaurantsByAdminId() {
        List<Restaurant> restaurants = service.getAllRestaurants(ADMIN_1.getId());
        assertEquals(restaurants, ADMIN1_RESTAURANTS);
    }

    @Test
    void getAllRestaurantsByWrongAdminId(){
        List <Restaurant> restaurants = service.getAllRestaurants(1);
        assertTrue(restaurants.size()==0);
    }

    @Test
    void createRestaurant() {
        Restaurant restaurant = service.createRestaurant(new Restaurant("TestRestaurant", "TestAddress", ADMIN_1));
        assertEquals(restaurant, CREATED_RESTAURANT);
    }


    @Test
    void deleteRestaurant() {
    }

    @Test
    void updateRestaurant() {
    }
}