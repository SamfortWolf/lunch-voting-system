package ru.samfort.voting.service;

import org.slf4j.Logger;
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
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class VotingService {

    private static final Logger log = getLogger(VotingService.class);
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;


    public VotingService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public ResponseEntity <List<Vote>> getAll (){
        return new ResponseEntity<>(voteRepository.getAllByUser_Id(SecurityUtil.getAuthUserId()), HttpStatus.OK);
    }

    public ResponseEntity<Vote> vote(int restaurant_id) {
        LocalDateTime now = LocalDateTime.now();
        Restaurant restaurant = restaurantRepository.getOne(restaurant_id);
        int user_id = SecurityUtil.getAuthUserId();
        boolean isTimeToVoteExpire = ValidationUtil.isTimeExpire(now);
        log.debug(String.format("today is: %s, restaurant_id is %d, user_id is %d, time is expire? %s", now.toString(), restaurant_id, user_id, isTimeToVoteExpire));
        Vote vote = voteRepository.findByUserIdAndDate(user_id, now.toLocalDate());
        if (vote == null) {
            vote = new Vote(userRepository.getOne(user_id), restaurant, now.toLocalDate());
            voteRepository.save(vote);
            log.debug("New vote created! "+vote.toString());
            return new ResponseEntity<>(vote, HttpStatus.CREATED);
        } else {
            if (isTimeToVoteExpire) {
                log.debug("Voting time is expire and you already made a vote");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                log.debug("Vote updated");
                return new ResponseEntity<>(voteRepository.save(new Vote(userRepository.getOne(user_id), restaurant, now.toLocalDate())), HttpStatus.OK);
            }
        }
    }
}
