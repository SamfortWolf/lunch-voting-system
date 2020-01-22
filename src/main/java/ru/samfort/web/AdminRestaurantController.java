package ru.samfort.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.samfort.model.Restaurant;
import ru.samfort.service.AdminRestaurantService;
import ru.samfort.util.SecurityUtil;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest/admin/restaurants")
public class AdminRestaurantController {
    private static final Logger log = getLogger(AdminRestaurantController.class);

    @Autowired
    AdminRestaurantService service;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll () {
        int admin_id = SecurityUtil.getAuthUserId();
        List <Restaurant> restaurants = service.getAllRestaurants(admin_id);
        log.info("getAll restaurants for admin {}", admin_id);
        return restaurants;
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create (@RequestBody Restaurant restaurant){
        int admin_id = SecurityUtil.getAuthUserId();
        restaurant.setUser(service.getUserById(admin_id));
        Restaurant newRestaurant = service.createRestaurant(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/restaurants" + "/{id}")
                .buildAndExpand(newRestaurant.getId()).toUri();
        log.info("create restaurant {} for admin {}", newRestaurant, admin_id);
        return ResponseEntity.created(uriOfNewResource).body(newRestaurant);
    }
    @DeleteMapping
    public void delete (@RequestParam int restaurant_id) {
        int admin_id = SecurityUtil.getAuthUserId();
        service.deleteRestaurant(restaurant_id, admin_id);
        log.info("delete restaurant with id {}", restaurant_id);
    }
    @PutMapping
    public void update (@RequestBody Restaurant restaurant, @RequestParam int restaurant_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.updateRestaurant(restaurant, restaurant_id, admin_id);
        log.info("update restaurant {} to {}", restaurant_id, restaurant);
    }
}
