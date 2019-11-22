package ru.samfort.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samfort.voting.model.Restaurant;

public interface RestaurantRepository extends JpaRepository <Restaurant, Integer> {
}
