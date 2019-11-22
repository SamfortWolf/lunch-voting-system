package ru.samfort.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samfort.voting.model.Dish;

public interface DishRepository extends JpaRepository <Dish, Integer> {
}
