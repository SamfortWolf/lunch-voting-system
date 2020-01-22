package ru.samfort.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.samfort.model.Menu;
import ru.samfort.service.AdminMenuService;
import ru.samfort.util.SecurityUtil;

import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest/admin/menus")
public class AdminMenuController {
    private static final Logger log = getLogger(AdminMenuController.class);

    @Autowired
    AdminMenuService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAll (@RequestParam int restaurant_id) {
        int admin_id = SecurityUtil.getAuthUserId();
        List <Menu> menus = service.getAllMenus(restaurant_id, admin_id);
        log.info("getAll menus from restaurant {} for admin {}", restaurant_id, admin_id);
        return menus;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create (@RequestBody Menu menu, @RequestParam int restaurant_id){
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

    @DeleteMapping
    public void delete (@RequestParam int menu_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.deleteMenu(menu_id, admin_id);
        log.info("delete menu with id {}", menu_id);
    }

    @PutMapping
    public void update (@RequestBody Menu menu, @RequestParam int menu_id){
        int admin_id = SecurityUtil.getAuthUserId();
        service.updateMenu(menu, menu_id, admin_id);
        log.info("update menu {} to {}", menu_id, menu);
    }
}
