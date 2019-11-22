package ru.samfort.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samfort.voting.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
