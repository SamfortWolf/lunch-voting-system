package ru.samfort.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.samfort.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;


public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.user.id=?1 AND v.date=?2")
    Vote findByUserIdAndDate(int user_id, LocalDate toLocalDate);

    @Query("select v from Vote v where v.user.id=?1 ORDER BY v.date desc ")
    List<Vote> getAllByUser_Id (int user_id);

}
