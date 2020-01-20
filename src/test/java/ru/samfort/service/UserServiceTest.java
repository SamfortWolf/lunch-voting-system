package ru.samfort.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.samfort.model.Vote;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.samfort.TestData.*;

class UserServiceTest extends AbstractServiceTest{

    @Autowired
    private UserService userService;

    @Test
    void getAll() {
        List <Vote> votes = userService.getAll(USER.getId());
        assertEquals(votes, VOTES);
    }

    @Test
    void newVote() {
        Vote testVote = userService.vote(100004, USER.getId());
        assertEquals(List.of(testVote), userService.getAll(USER.getId()));
    }

    @Test
    void updateVote (){
        Vote testVote = userService.vote(100004, USER.getId());

    }

    @Test
    void expireVote () {

    }

    @Test
    void getAllRestaurants() {
    }

    @Test
    void getAllMenus() {
    }

    @Test
    void getAllDishes() {
    }

    @Test
    void loadUserByUsername() {
    }
}