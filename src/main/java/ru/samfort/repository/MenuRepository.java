package ru.samfort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samfort.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
