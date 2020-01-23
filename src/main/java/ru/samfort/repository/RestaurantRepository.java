package ru.samfort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.samfort.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r WHERE r.user.id=:admin_id")
    List<Restaurant> findAllById(@Param("admin_id") int admin_id);

    @Query("SELECT r FROM Restaurant r WHERE r.id=:restaurant_id AND r.user.id=:admin_id")
    Optional <Restaurant> findByIdAndUserId(@Param("restaurant_id") int restaurant_id, @Param("admin_id") int admin_id);
}
