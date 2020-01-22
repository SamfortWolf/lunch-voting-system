package ru.samfort.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samfort.model.Restaurant;
import ru.samfort.model.User;
import ru.samfort.repository.RestaurantRepository;
import ru.samfort.repository.UserRepository;
import ru.samfort.util.exception.NotFoundException;

import java.util.List;

@Service
public class AdminRestaurantService {

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminRestaurantService(UserRepository userRepository,
                            RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public User getUserById(int user_id) {
        User user = userRepository.getById(user_id);
        if (user == null) {
            throw new NotFoundException("User with id " + user_id + " is not found");
        }
        return user;
    }

    public Restaurant getRestaurantById(int restaurant_id) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id);
        if (restaurant == null){
            throw new NotFoundException("Restaurant with id " + restaurant_id + " is not found");
        }
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants(int admin_id) {
        return restaurantRepository.findAllById(admin_id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(int restaurant_id, int admin_id) {
        Restaurant restaurant = restaurantRepository.findByIdAndUserId(restaurant_id, admin_id).orElse(null);
        if (restaurant == null) {
            throw new NotFoundException(String.format("Restaurant with id %d of admin %d not found", restaurant_id, admin_id));
        } else
            restaurantRepository.delete(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant, int restaurant_id, int admin_id) {
        Restaurant originalRestaurant = restaurantRepository.findByIdAndUserId(restaurant_id, admin_id).orElse(null);
        if (originalRestaurant == null) {
            throw new NotFoundException(String.format("Restaurant with id %d of admin %d not found", restaurant_id, admin_id));
        } else if (originalRestaurant.getId() == restaurant_id) {
            restaurant.setId(restaurant_id);
            restaurant.setUser(getUserById(admin_id));
            restaurantRepository.save(restaurant);
        }
    }

}
