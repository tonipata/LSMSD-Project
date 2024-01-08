package it.unipi.lsmsd.gamehub.repository;

import it.unipi.lsmsd.gamehub.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface GameRepository extends MongoRepository<Game,String> {
    List<Game> findByName(String name);
    List<Game> findByGenres(String genres);
    List<Game> findByAvgScoreGreaterThanEqual(int avgScore);
    List<Game> findByGenresAndAvgScoreGreaterThanEqual(String genres,int avgScore);

}
