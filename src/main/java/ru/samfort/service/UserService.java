package ru.samfort.service;

import org.springframework.stereotype.Service;
import ru.samfort.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
