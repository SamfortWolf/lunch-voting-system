package ru.samfort.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;
import ru.samfort.model.User;
import ru.samfort.repository.MenuRepository;
import ru.samfort.repository.RestaurantRepository;
import ru.samfort.repository.UserRepository;
import ru.samfort.util.exception.NotFoundException;

import java.util.List;

@Service
public class AdminMenuService {

    private MenuRepository menuRepository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminMenuService(MenuRepository menuRepository, UserRepository userRepository,
                            RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public User getUserById(int user_id) {
        User user = userRepository.getById(user_id);
        if (user == null) {
            throw new NotFoundException("User with id " + user_id + " is not found");
        }
        return user;
    }

    public Restaurant getRestaurantById(int restaurant_id) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id);
        if (restaurant == null){
            throw new NotFoundException("Restaurant with id " + restaurant_id + " is not found");
        }
        return restaurant;
    }

    public List<Menu> getAllMenus(int restaurant_id, int admin_id) {
        return menuRepository.findAllByRestaurantIdAndUserId(restaurant_id, admin_id);
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(int menu_id, int admin_id) {
        Menu menu = menuRepository.findByIdAndUserId(menu_id, admin_id).orElse(null);
        if (menu == null) {
            throw new NotFoundException(String.format("Menu with id %d of admin %d not found", menu_id, admin_id));
        } else
            menuRepository.delete(menu);
    }

    public void updateMenu(Menu menu, int menu_id, int admin_id) {
        Menu originalMenu = menuRepository.findByIdAndUserId(menu_id, admin_id).orElse(null);
        if (originalMenu == null) {
            throw new NotFoundException(String.format("Menu with id %d of admin %d not found", menu_id, admin_id));
        } else if (originalMenu.getId() == menu_id) {
            menu.setId(menu_id);
            menu.setUser(getUserById(admin_id));
            menu.setRestaurant(restaurantRepository.findById(originalMenu.getRestaurant().getId()).get());
            menuRepository.save(menu);
        }
    }
}
