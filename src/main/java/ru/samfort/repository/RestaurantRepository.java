package ru.samfort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.samfort.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findById (int id);
}
