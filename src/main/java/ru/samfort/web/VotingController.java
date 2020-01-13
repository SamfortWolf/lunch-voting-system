package ru.samfort.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.samfort.model.Vote;
import ru.samfort.service.VotingService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/votes")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll (){
        System.out.println("getAll");
        return votingService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Vote vote(@RequestParam(value = "id") int id){
        System.out.println("vote for restaurant #"+id);
        return votingService.vote(id);
    }
}
