package ru.samfort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.samfort.model.Menu;
import ru.samfort.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu getById(int menu_id);

    List<Menu> findAllByRestaurantId(int restaurant_id);

    @Query("SELECT m FROM Menu m WHERE m.user.id=:admin_id AND m.restaurant.id=:restaurant_id")
    List<Menu> findAllByRestaurantIdAndUserId(@Param("restaurant_id") int restaurant_id, @Param("admin_id") int admin_id);

    @Query("SELECT m FROM Menu m WHERE m.id=:menu_id AND m.user.id=:admin_id")
    Optional<Menu> findByIdAndUserId(@Param("menu_id") int menu_id, @Param("admin_id") int admin_id);

}
