package ru.samfort.repository;

import org.springframework.data.repository.CrudRepository;
import ru.samfort.model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    Restaurant findById (int id);
}
