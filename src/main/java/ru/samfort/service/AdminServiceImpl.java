package ru.samfort.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;
import ru.samfort.model.User;
import ru.samfort.repository.DishRepository;
import ru.samfort.repository.MenuRepository;
import ru.samfort.repository.RestaurantRepository;
import ru.samfort.repository.UserRepository;
import ru.samfort.util.exception.NotFoundException;

import java.util.List;

@Service
public class AdminServiceImpl {

    private DishRepository dishRepository;
    private MenuRepository menuRepository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(DishRepository dishRepository, MenuRepository menuRepository, UserRepository userRepository,
                            RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
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

    public Menu getMenuById(int menu_id) {
        Menu menu = menuRepository.getById(menu_id);
        if (menu == null){
            throw new NotFoundException("Menu with id " + menu_id + " is not found");
        }
        return menu;
    }

    //    RESTAURANTS
    public List<Restaurant> getAllRestaurants(int admin_id) {
        return restaurantRepository.findAllById(admin_id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(int restaurant_id, int admin_id) {
        Restaurant restaurant = restaurantRepository.findByIdAndUserId(restaurant_id, admin_id).orElse(null);
        if (restaurant == null) {
            throw new NotFoundException(String.format("Restaurant with id %d of admin %d not found", restaurant_id, admin_id));
        } else
            restaurantRepository.delete(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant, int restaurant_id, int admin_id) {
        Restaurant originalRestaurant = restaurantRepository.findByIdAndUserId(restaurant_id, admin_id).orElse(null);
        if (originalRestaurant == null) {
            throw new NotFoundException(String.format("Restaurant with id %d of admin %d not found", restaurant_id, admin_id));
        } else if (originalRestaurant.getId() == restaurant_id) {
            restaurant.setId(restaurant_id);
            restaurant.setUser(getUserById(admin_id));
            restaurantRepository.save(restaurant);
        }
    }

    //    MENUS
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

//    DISHES
    public List<Dish> getAllDishes(int menu_id, int admin_id) {
        return dishRepository.findAllByMenuIdAndUserId(menu_id, admin_id);
    }

    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public void deleteDish(int dish_id, int admin_id) {
        Dish dish = dishRepository.findByIdAndUserId(dish_id, admin_id).orElse(null);
        if (dish == null){
            throw new NotFoundException(String.format("Dish with id %d of admin %d not found", dish_id, admin_id));
        }
        else
            dishRepository.delete(dish);
    }

    public void updateDish(Dish dish, int dish_id, int admin_id) {
        Dish originalDish = dishRepository.findByIdAndUserId(dish_id, admin_id).orElse(null);
        if (originalDish == null){
            throw new NotFoundException(String.format("Dish with id %d of admin %d not found", dish_id, admin_id));
        }
        else if (originalDish.getId() == dish_id){
            dish.setId(dish_id);
            dish.setUser(getUserById(admin_id));
            dish.setMenu(menuRepository.findById(originalDish.getMenu().getId()).get());
            dishRepository.save(dish);
        }
    }
}
