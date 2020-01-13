package ru.samfort.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.samfort.model.Dish;
import ru.samfort.service.AdminService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/admin")
public class AdminController {

    @Autowired
    AdminService service;

    @GetMapping(value = "/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllDishes (@RequestParam(value = "menu_id") int menu_id){
        return service.getAllDishes(menu_id);
    }

    @PostMapping(value = "/dishes/{menu_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Dish> createDish (@RequestBody Dish dish, @PathVariable int menu_id) {
        dish.setMenu(service.getMenuById(menu_id));
        Dish newDish = service.createOrUpdateDish(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin" + "/{id}")
                .buildAndExpand(newDish.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(newDish);
    }
}
