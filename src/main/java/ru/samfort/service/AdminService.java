package ru.samfort.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samfort.model.Dish;
import ru.samfort.model.Menu;
import ru.samfort.repository.DishRepository;
import ru.samfort.repository.MenuRepository;

import java.util.List;

@Service
public class AdminService {

    private DishRepository dishRepository;
    private MenuRepository menuRepository;

    @Autowired
    public AdminService(DishRepository dishRepository, MenuRepository menuRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    public List<Dish> getAllDishes (int menu_id){
        return dishRepository.getAllByMenuId(menu_id);
    }

    public Dish createOrUpdateDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Menu getMenuById(int menu_id) {
        return menuRepository.getById(menu_id);
    }
}
