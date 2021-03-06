package ru.samfort.repository;

import org.springframework.data.repository.CrudRepository;
import ru.samfort.model.Vote;

import java.time.LocalDate;
import java.util.List;


public interface VoteRepository extends CrudRepository<Vote, Integer> {

//    @Query("SELECT v FROM Vote v WHERE v.user.id=?1 AND v.date=?2")
    Vote findByUserIdAndDate(int user_id, LocalDate toLocalDate);

//    @Query("select v from Vote v where v.user.id=?1 ORDER BY v.date desc ")
    List<Vote> getAllByUserId(int user_id);

}
