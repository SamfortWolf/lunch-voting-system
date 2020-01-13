package ru.samfort.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.model.User;
import ru.samfort.repository.DishRepository;
import ru.samfort.repository.MenuRepository;
import ru.samfort.repository.UserRepository;

import java.util.List;

@Service
public class AdminService {

    private DishRepository dishRepository;
    private MenuRepository menuRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminService(DishRepository dishRepository, MenuRepository menuRepository, UserRepository userRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
    }

    public User getUser (int user_id) { return  userRepository.getById(user_id);}

    public List<Dish> getAllDishes (int menu_id){
        return dishRepository.getAllByMenuId(menu_id);
    }

    public Dish createOrUpdateDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Menu getMenu(int menu_id) {
        return menuRepository.getById(menu_id);
    }
}
