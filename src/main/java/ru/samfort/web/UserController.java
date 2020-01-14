package ru.samfort.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;
import ru.samfort.model.Vote;
import ru.samfort.service.UserService;
import ru.samfort.util.SecurityUtil;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest/vote")
public class UserController {
    private static final Logger log = getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllVotes (){
        int user_id = SecurityUtil.getAuthUserId();
        List<Vote> votes = userService.getAll(user_id);
        log.info("GetAll votes");
        return votes;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> vote(@RequestParam(value = "id") int id){
        int user_id = SecurityUtil.getAuthUserId();
        Vote newVote = userService.vote(id, user_id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/vote" + "/{id}")
                .buildAndExpand(newVote.getId()).toUri();
        log.info("create vote {} for restaurant {} for user {}", newVote, id, user_id);
        return ResponseEntity.created(uriOfNewResource).body(newVote);
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