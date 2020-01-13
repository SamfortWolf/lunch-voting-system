package ru.samfort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samfort.model.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu getById(int menu_id);

    List<Menu> findAllByRestaurantId(int restaurant_id);
}
