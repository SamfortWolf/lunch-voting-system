package ru.samfort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samfort.model.User;

public interface UserRepository extends JpaRepository <User, Integer> {
    User getById(int user_id);

    User getByEmail(String email);
}
