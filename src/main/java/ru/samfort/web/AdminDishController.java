package ru.samfort.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.samfort.model.Dish;
import ru.samfort.service.AdminDishService;
import ru.samfort.service.AdminMenuService;
import ru.samfort.util.SecurityUtil;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest/admin/dishes")
public class AdminDishController {
    private static final Logger log = getLogger(AdminDishController.class);

    @Autowired
    AdminDishService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll (@RequestParam int menu_id){
        int admin_id = SecurityUtil.getAuthUserId();
        List<Dish> dishes = service.getAllDishes(menu_id, admin_id);
        log.info("getAll dishes for admin {} from menu {}", admin_id, menu_id);
        return dishes;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create (@RequestBody Dish dish, @RequestParam int menu_id) {
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

    @DeleteMapping
    public void delete (@RequestParam int dish_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.deleteDish(dish_id, admin_id);
        log.info("delete dish with id {}", dish_id);
    }

    @PutMapping
    public void update (@RequestBody Dish dish, @RequestParam int dish_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.updateDish(dish, dish_id, admin_id);
        log.info("update dish {} to {}", dish_id, dish);
    }
}
