package ru.samfort.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.samfort.model.*;
import ru.samfort.repository.*;
import ru.samfort.util.ValidationUtil;
import ru.samfort.util.exception.NotFoundException;
import ru.samfort.web.LoggedUser;

import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserService implements UserDetailsService {

    private static final Logger log = getLogger(UserService.class);
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;

    @Autowired
    public UserService(VoteRepository voteRepository, UserRepository userRepository,
                       RestaurantRepository restaurantRepository, MenuRepository menuRepository, DishRepository dishRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
    }


    public List<Vote> getAll(int user_id) {
        return voteRepository.getAllByUserId(user_id);
    }

    public Vote vote(int restaurant_id, boolean isTimeToVoteExpire, int user_id) {
        LocalDateTime now = LocalDateTime.now();
        Restaurant restaurant = restaurantRepository.findById(restaurant_id);
        if (restaurant == null) {
            throw new NotFoundException(String.format("Restaurant with id %s not found", restaurant_id));
        }
//        boolean isTimeToVoteExpire = ValidationUtil.isTimeExpire(now);
        log.info(String.format("today is: %s, restaurant_id is %d, user_id is %d, time is expire? %s", now.toString(),
                restaurant_id, user_id, isTimeToVoteExpire));
        Vote vote = voteRepository.findByUserIdAndDate(user_id, now.toLocalDate());
        if (vote == null) {
            vote = new Vote(userRepository.getOne(user_id), restaurant, now.toLocalDate());
            voteRepository.save(vote);
            log.info("New vote created: " + vote.toString());
            return vote;
        } else {
            if (isTimeToVoteExpire) {
                log.info("Voting time is expire and you already made a vote");
                return vote;//need to change return value
            } else {
                vote.setRestaurant(restaurant);
                voteRepository.save(vote);
                log.info("Vote updated");
                return vote;
            }
        }
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public List<Menu> getAllMenus () {return menuRepository.findAll();}

    public List<Menu> getMenusByRestaurantId(int restaurant_id) {
        return menuRepository.findAllByRestaurantId(restaurant_id);
    }

    public List<Dish> getAllDishes () {return dishRepository.findAll();}

    public List<Dish> getDishesByMenuId(int menu_id) {
        return dishRepository.findAllByMenuId(menu_id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        log.info("User roles is {}", user.getRoles());
        return new LoggedUser(user);
    }
}
