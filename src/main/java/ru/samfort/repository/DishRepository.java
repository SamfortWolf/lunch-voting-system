package ru.samfort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samfort.model.Dish;

public interface DishRepository extends JpaRepository <Dish, Integer> {
}
