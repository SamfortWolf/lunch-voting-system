package ru.samfort.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.model.User;
import ru.samfort.repository.DishRepository;
import ru.samfort.repository.MenuRepository;
import ru.samfort.repository.RestaurantRepository;
import ru.samfort.repository.UserRepository;
import ru.samfort.util.exception.NotFoundException;

import java.util.List;

@Service
public class AdminDishService {

    private DishRepository dishRepository;
    private MenuRepository menuRepository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminDishService(DishRepository dishRepository, MenuRepository menuRepository, UserRepository userRepository,
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

    public Menu getMenuById(int menu_id) {
        Menu menu = menuRepository.findById(menu_id);
        if (menu == null){
            throw new NotFoundException("Menu with id " + menu_id + " is not found");
        }
        return menu;
    }

    public Dish getDishById(int dish_id, int admin_id) {
        Dish dish = dishRepository.findByIdAndUserId(dish_id, admin_id).orElse(null);
        if (dish == null){
            throw new NotFoundException("Dish with id " + dish_id + " is not found");
        }
        return dish;
    }

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
