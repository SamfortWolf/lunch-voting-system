package ru.samfort.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samfort.voting.model.Vote;
import ru.samfort.voting.service.VotingService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/vote")
public class VotingController {

    private VotingService votingService;

    @Autowired
    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vote>> getAll (){
        return votingService.getAll();
    }

    @PutMapping("/{restaurant_id}")
    public ResponseEntity <Vote> vote(@PathVariable int restaurant_id){
        return votingService.vote(restaurant_id);
    }


}
