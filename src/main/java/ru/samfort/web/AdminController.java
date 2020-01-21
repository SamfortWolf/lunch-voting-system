package ru.samfort.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;
import ru.samfort.service.AdminServiceImpl;
import ru.samfort.util.SecurityUtil;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest/admin")
public class AdminController {
    private static final Logger log = getLogger(AdminController.class);

    @Autowired
    AdminServiceImpl service;

//    RESTAURANTS
    @GetMapping (value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List <Restaurant> getAllRestaurants () {
        int admin_id = SecurityUtil.getAuthUserId();
        List <Restaurant> restaurants = service.getAllRestaurants(admin_id);
        log.info("getAll restaurants for admin {}", admin_id);
        return restaurants;
    }
    @PostMapping (value = "/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Restaurant> createRestaurant (@RequestBody Restaurant restaurant){
        int admin_id = SecurityUtil.getAuthUserId();
        restaurant.setUser(service.getUserById(admin_id));
        Restaurant newRestaurant = service.createRestaurant(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/restaurants" + "/{id}")
                .buildAndExpand(newRestaurant.getId()).toUri();
        log.info("create restaurant {} for admin {}", newRestaurant, admin_id);
        return ResponseEntity.created(uriOfNewResource).body(newRestaurant);
    }
    @DeleteMapping(value = "/restaurants")
    public void deleteRestaurant (@RequestParam int restaurant_id) {
        int admin_id = SecurityUtil.getAuthUserId();
        service.deleteRestaurant(restaurant_id, admin_id);
        log.info("delete restaurant with id {}", restaurant_id);
    }
    @PutMapping(value = "/restaurants")
    public void updateRestaurant (@RequestBody Restaurant restaurant, @RequestParam int restaurant_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.updateRestaurant(restaurant, restaurant_id, admin_id);
        log.info("update restaurant {} to {}", restaurant_id, restaurant);
    }

//    MENUS
    @GetMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List <Menu> getAllMenus (@RequestParam int restaurant_id) {
        int admin_id = SecurityUtil.getAuthUserId();
        List <Menu> menus = service.getAllMenus(restaurant_id, admin_id);
        log.info("getAll menus from restaurant {} for admin {}", restaurant_id, admin_id);
        return menus;
    }

    @PostMapping (value = "/menus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenu (@RequestBody Menu menu, @RequestParam int restaurant_id){
        int admin_id = SecurityUtil.getAuthUserId();
        menu.setUser(service.getUserById(admin_id));
        menu.setRestaurant(service.getRestaurantById(restaurant_id));
        Menu newMenu = service.createMenu(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/menus" + "/{id}")
                .buildAndExpand(newMenu.getId()).toUri();
        log.info("create menu {} for restaurant {} for admin {}", newMenu, restaurant_id, admin_id);
        return ResponseEntity.created(uriOfNewResource).body(newMenu);
    }

    @DeleteMapping(value = "/menus")
    public void deleteMenu (@RequestParam int menu_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.deleteMenu(menu_id, admin_id);
        log.info("delete menu with id {}", menu_id);
    }

    @PutMapping(value = "/menus")
    public void updateMenu (@RequestBody Menu menu, @RequestParam int menu_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.updateMenu(menu, menu_id, admin_id);
        log.info("update menu {} to {}", menu_id, menu);
    }

//    DISHES
    @GetMapping(value = "/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllDishes (@RequestParam int menu_id){
        int admin_id = SecurityUtil.getAuthUserId();
        List<Dish> dishes = service.getAllDishes(menu_id, admin_id);
        log.info("getAll dishes for admin {} from menu {}", admin_id, menu_id);
        return dishes;
    }

    @PostMapping(value = "/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Dish> createDish (@RequestBody Dish dish, @RequestParam int menu_id) {
        int admin_id = SecurityUtil.getAuthUserId();
        dish.setUser(service.getUserById(admin_id));
        dish.setMenu(service.getMenuById(menu_id));
        Dish newDish = service.createDish(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/dishes" + "/{id}")
                .buildAndExpand(newDish.getId()).toUri();
        log.info("create dish {} for menu {} for admin {}", newDish, menu_id, admin_id);
        return ResponseEntity.created(uriOfNewResource).body(newDish);
    }

    @DeleteMapping(value = "/dishes")
    public void deleteDish (@RequestParam int dish_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.deleteDish(dish_id, admin_id);
        log.info("delete dish with id {}", dish_id);
    }

    @PutMapping(value = "/dishes")
    public void updateDish (@RequestBody Dish dish, @RequestParam int dish_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.updateDish(dish, dish_id, admin_id);
        log.info("update dish {} to {}", dish_id, dish);
    }
}
