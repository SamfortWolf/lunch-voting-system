package ru.samfort.voting.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.samfort.voting.model.Restaurant;
import ru.samfort.voting.model.Vote;
import ru.samfort.voting.repository.RestaurantRepository;
import ru.samfort.voting.repository.UserRepository;
import ru.samfort.voting.repository.VoteRepository;
import ru.samfort.voting.util.SecurityUtil;
import ru.samfort.voting.util.ValidationUtil;

import java.time.LocalDateTime;

@Service
public class VotingService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;


    public VotingService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public ResponseEntity<Vote> vote(int restaurant_id) {
        LocalDateTime now = LocalDateTime.now();
        Restaurant restaurant = restaurantRepository.getOne(restaurant_id);
        int user_id = SecurityUtil.getAuthUserId();
        boolean isTimeToVoteExpire = ValidationUtil.isTimeExpire(now);
        Vote vote = voteRepository.findByUserIdAndDate(user_id, now.toLocalDate());
        if (vote == null) {
            vote = new Vote(userRepository.getOne(user_id), restaurant, now.toLocalDate());
            return new ResponseEntity<>(vote, HttpStatus.CREATED);
        } else {
            if (isTimeToVoteExpire) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(voteRepository.save(new Vote(userRepository.getOne(user_id), restaurant, now.toLocalDate())), HttpStatus.OK);
            }
        }
    }
}
