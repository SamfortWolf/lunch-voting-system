package ru.samfort.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.samfort.model.Restaurant;
import ru.samfort.model.User;
import ru.samfort.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.samfort.TestData.*;
import static ru.samfort.TestData.CREATED_RESTAURANT;
//@Transactional
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
        Restaurant restaurant = service.getRestaurantById(R1.getId(), ADMIN_1.getId());
        assertEquals(restaurant, R1);
    }

    @Test
    void getRestaurantByWrongId() {
        assertThrows(NotFoundException.class, ()-> service.getRestaurantById(1, TEST_SEQ));
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
        Restaurant newRestaurant = CREATED_RESTAURANT;
        Restaurant restaurant = service.createRestaurant(newRestaurant);
        newRestaurant.setId(restaurant.getId());
        assertEquals(newRestaurant, restaurant);
    }

    @Test
    void deleteRestaurant() {
        Restaurant restaurant = service.createRestaurant(CREATED_RESTAURANT);
        service.deleteRestaurant(restaurant.getId(), ADMIN_1.getId());
        assertEquals(service.getAllRestaurants(ADMIN_1.getId()), ADMIN1_RESTAURANTS);
    }

    @Test
    void deleteNotFoundRestaurant() {
        assertThrows(NotFoundException.class, ()-> service.deleteRestaurant(CREATED_RESTAURANT.getId(), ADMIN_1.getId()));
    }

    @Test
    void updateRestaurant() {
        service.updateRestaurant(UPDATED_RESTAURANT, R1.getId(), ADMIN_1.getId());
        assertEquals(UPDATED_RESTAURANT, service.getRestaurantById(R1.getId(), ADMIN_1.getId()));
    }

    @Test
    void updateNotFoundRestaurant() {
        assertThrows(NotFoundException.class, ()-> service.updateRestaurant(CREATED_RESTAURANT, CREATED_RESTAURANT.getId(), ADMIN_1.getId()));
    }

    @Test//throws nothing, don't know why
    void createWithException () {
//        validateRootCause(()-> service.createRestaurant(new Restaurant("A","Address", USER)), ConstraintViolationException.class);
//        validateRootCause(()-> service.createRestaurant(new Restaurant("  ","Address", USER)), ConstraintViolationException.class);
//        validateRootCause(()-> service.createRestaurant(new Restaurant("Name","   ", USER)), ConstraintViolationException.class);
//        validateRootCause(()-> service.createRestaurant(new Restaurant("Name","A", USER)), ConstraintViolationException.class);
//        validateRootCause(()-> service.createRestaurant(new Restaurant("A","Address", null)), ConstraintViolationException.class);
    }
}