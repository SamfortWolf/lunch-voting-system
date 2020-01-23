package ru.samfort.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.model.User;
import ru.samfort.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.samfort.TestData.*;
//@Transactional
class AdminDishServiceTest extends AbstractServiceTest {

    @Autowired
    AdminDishService service;

    @Test
    void getUserById() {
        User user = service.getUserById(USER.getId());
        assertEquals(user, USER);
    }

    @Test
    void getUserByWrongId() {
        assertThrows(NotFoundException.class, () -> service.getUserById(1));
    }

    @Test
    void getMenuById() {
        Menu menu = service.getMenuById(M1.getId());
        assertEquals(M1, menu);
    }

    @Test
    void getMenuByWrongId() {
        assertThrows(NotFoundException.class, () -> service.getMenuById(1));
    }

    @Test
    void getAllDishesByMenuIdAndAdminId() {
        List<Dish> dishes = service.getAllDishes(M1.getId(), ADMIN_1.getId());
        assertEquals(M1_DISHES, dishes);
    }

    @Test
    void getAllDishesByWrongMenuId() {
        List<Dish> dishes = service.getAllDishes(1, ADMIN_1.getId());
        assertTrue(dishes.size() == 0);
    }

    @Test
    void getAllDishesByWrongAdminId() {
        List<Dish> dishes = service.getAllDishes(M1.getId(), 1);
        assertTrue(dishes.size() == 0);
    }

    @Test
    void createDish() {
        Dish newDish = CREATED_DISH;
        Dish dish = service.createDish(newDish);
        newDish.setId(dish.getId());
        assertEquals(newDish, dish);
    }

    @Test
    void deleteDish() {
        Dish dish = service.createDish(CREATED_DISH);
        service.deleteDish(dish.getId(), ADMIN_1.getId());
        assertEquals(M1_DISHES, service.getAllDishes(M1.getId(), ADMIN_1.getId()));
    }

    @Test
    void deleteNotFoundDish() {
        assertThrows(NotFoundException.class, ()-> service.deleteDish(CREATED_DISH.getId(), ADMIN_1.getId()));
    }

    @Test
    void updateDish() {
        service.updateDish(UPDATED_DISH, D1.getId(), ADMIN_1.getId());
        assertEquals(UPDATED_DISH, service.getDishById(D1.getId(), ADMIN_1.getId()));
    }
    @Test
    void updateNotFoundDish() {
        assertThrows(NotFoundException.class, ()->service.updateDish(UPDATED_DISH, UPDATED_DISH.getId(), ADMIN_1.getId()));

    }
}