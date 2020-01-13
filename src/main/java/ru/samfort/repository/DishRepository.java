package ru.samfort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samfort.model.Dish;

import java.util.List;

public interface DishRepository extends JpaRepository <Dish, Integer> {

    List<Dish> getAllByMenuId (int menu_id);
}
