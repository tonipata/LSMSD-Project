package it.unipi.lsmsd.gamehub.service.impl;

import it.unipi.lsmsd.gamehub.DTO.GameDTO;
import it.unipi.lsmsd.gamehub.DTO.GameDTOAggregation;
import it.unipi.lsmsd.gamehub.DTO.GameDTOAggregation2;
import it.unipi.lsmsd.gamehub.model.Game;
import it.unipi.lsmsd.gamehub.repository.GameRepository;
import it.unipi.lsmsd.gamehub.repository.MongoDBAggregation.GameRepositoryCustom;
import it.unipi.lsmsd.gamehub.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> retrieveGamesByParameters(GameDTO gameDTO) {
        try {
            if(gameDTO.getName()!=null){
                return gameRepository.findByName(gameDTO.getName());
            } else if (gameDTO.getGenres() != null && gameDTO.getAvgScore() != 0) {
                // Both score and genres are provided
                return gameRepository.findByGenresAndAvgScoreGreaterThanEqual(gameDTO.getGenres(), gameDTO.getAvgScore());
            } else if (gameDTO.getGenres() != null) {
                // Only genres are provided
                return gameRepository.findByGenres(gameDTO.getGenres());
            } else if (gameDTO.getAvgScore() != 0) {
                // Only score are provided
                return gameRepository.findByAvgScoreGreaterThanEqual(gameDTO.getAvgScore());
            } else {
                // No specific criteria, return null
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<GameDTOAggregation> retrieveAggregateGamesByGenresAndSortByScore() {
        try{
            List<GameDTOAggregation> gameList= gameRepository.findAggregation();
            if(!gameList.isEmpty()){
                return gameList;
            }
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


    }

    @Override
    public List<GameDTOAggregation2> findAggregation4() {
        try {
            return gameRepository.findAggregation4();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }




}
