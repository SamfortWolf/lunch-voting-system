package ru.samfort.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;
import ru.samfort.model.User;
import ru.samfort.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.samfort.TestData.*;

//@Transactional
class AdminMenuServiceTest extends AbstractServiceTest {

    @Autowired
    AdminMenuService service;

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
        assertThrows(NotFoundException.class, ()-> service.getRestaurantById(1, ADMIN_1.getId()));
    }

    @Test
    void getAllMenusByRestaurantAndAdminId() {
        List<Menu> menus = service.getAllMenus(R1.getId(), ADMIN_1.getId());
        assertEquals(R1_MENUS, menus);
    }

    @Test
    void getAllMenusByWrongRestaurantId() {
        List<Menu> menus = service.getAllMenus(1, ADMIN_1.getId());
        assertTrue(menus.size()==0);
    }

    @Test
    void getAllMenusByWrongAdminId() {
        List<Menu> menus = service.getAllMenus(R1.getId(), 1);
        assertTrue(menus.size()==0);
    }

    @Test
    void createMenu() {
        Menu newMenu = CREATED_MENU;
        Menu menu = service.createMenu(newMenu);
        newMenu.setId(menu.getId());
        assertEquals(newMenu, menu);
    }

    @Test
    void deleteMenu() {
        Menu menu = service.createMenu(CREATED_MENU);
        service.deleteMenu(menu.getId(), ADMIN_1.getId());
        assertEquals(service.getAllMenus(R1.getId(),ADMIN_1.getId()),R1_MENUS);
    }

    @Test
    void deleteNotFoundMenu() {
        assertThrows(NotFoundException.class, ()-> service.deleteMenu(CREATED_MENU.getId(), ADMIN_1.getId()));
    }

    @Test
    void updateMenu() {
        service.updateMenu(UPDATED_MENU, M1.getId(), ADMIN_1.getId());
        assertEquals(UPDATED_MENU, service.getMenuById(M1.getId()));
    }

    @Test
    void updateNotFoundMenu () {
        assertThrows(NotFoundException.class, ()-> service.updateMenu(UPDATED_MENU, 1, ADMIN_1.getId()));
    }
}