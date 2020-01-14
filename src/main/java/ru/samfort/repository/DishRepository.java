package ru.samfort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.samfort.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository <Dish, Integer> {
    
    List<Dish> findAllByMenuId(int menu_id);

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menu_id AND d.user.id=:admin_id")
    List<Dish> findAllByMenuIdAndUserId(@Param("menu_id")int menu_id, @Param("admin_id") int admin_id);

    @Query("SELECT d FROM Dish d WHERE d.id=:dish_id AND d.user.id=:admin_id")
    Optional<Dish> findByIdAndUserId(@Param("dish_id") int dish_id, @Param("admin_id") int admin_id);
}
