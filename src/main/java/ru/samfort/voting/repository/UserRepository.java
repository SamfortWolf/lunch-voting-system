package ru.samfort.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samfort.voting.model.User;

public interface UserRepository extends JpaRepository <User, Integer> {
}
