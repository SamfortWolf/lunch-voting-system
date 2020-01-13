package ru.samfort.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;
import ru.samfort.model.Vote;
import ru.samfort.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/vote")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllVotes (){
        System.out.println("getAll");
        return userService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Vote vote(@RequestParam(value = "id") int id){
        System.out.println("vote for restaurant #"+id);
        return userService.vote(id);
    }

    @GetMapping(value = "/restaurants")
    public List <Restaurant> GetAllRestaurants () {
        return userService.getAllRestaurants();
    }

    @GetMapping(value = "/menus")
    public List <Menu> GetAllMenus (@RequestParam int restaurant_id) {
        return userService.getAllMenus(restaurant_id);
    }

    @GetMapping(value = "/dishes")
    public List <Dish> GetAllDishes (@RequestParam int menu_id) {
        return userService.getAllDishes(menu_id);
    }
}
