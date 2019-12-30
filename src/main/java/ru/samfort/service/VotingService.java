package ru.samfort.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samfort.model.Restaurant;
import ru.samfort.model.Vote;
import ru.samfort.repository.RestaurantRepository;
import ru.samfort.repository.UserRepository;
import ru.samfort.repository.VoteRepository;
import ru.samfort.util.ValidationUtil;
import ru.samfort.util.exception.NotFoundException;
import ru.samfort.util.SecurityUtil;

import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class VotingService {

    private static final Logger log = getLogger(VotingService.class);
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VotingService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public List<Vote> getAll () {
        return voteRepository.getAllByUserId(SecurityUtil.getAuthUserId());
    }

    public Vote vote(int restaurant_id) {
        LocalDateTime now = LocalDateTime.now();
        Restaurant restaurant = restaurantRepository.findById(restaurant_id);
        if (restaurant==null){
            throw new NotFoundException(String.format("Restaurant with id %s not found", restaurant_id));
        }
        int user_id = SecurityUtil.getAuthUserId();
        boolean isTimeToVoteExpire = ValidationUtil.isTimeExpire(now);
        log.debug(String.format("today is: %s, restaurant_id is %d, user_id is %d, time is expire? %s", now.toString(), restaurant_id, user_id, isTimeToVoteExpire));
        Vote vote = voteRepository.findByUserIdAndDate(user_id, now.toLocalDate());
        if (vote == null) {
            vote = new Vote(userRepository.getOne(user_id), restaurant, now.toLocalDate());
            voteRepository.save(vote);
            log.debug("New vote created: "+vote.toString());
            return vote;
        } else {
            if (isTimeToVoteExpire) {
                log.debug("Voting time is expire and you already made a vote");
                return null;
            } else {
                vote.setRestaurant(restaurant);
                voteRepository.save(vote);
                log.debug("Vote updated");
                return vote;
            }
        }
    }
}
