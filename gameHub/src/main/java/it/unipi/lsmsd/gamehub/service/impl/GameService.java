package it.unipi.lsmsd.gamehub.service.impl;

import it.unipi.lsmsd.gamehub.DTO.GameDTO;
import it.unipi.lsmsd.gamehub.model.Game;
import it.unipi.lsmsd.gamehub.repository.GameRepository;
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
    }




}