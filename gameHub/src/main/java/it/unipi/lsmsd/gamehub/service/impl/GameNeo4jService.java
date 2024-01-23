package it.unipi.lsmsd.gamehub.service.impl;


import it.unipi.lsmsd.gamehub.model.Game;
import it.unipi.lsmsd.gamehub.model.UserNeo4j;
import it.unipi.lsmsd.gamehub.repository.GameNeo4jRepository;
import it.unipi.lsmsd.gamehub.repository.GameRepository;
import it.unipi.lsmsd.gamehub.repository.LoginRepository;
import it.unipi.lsmsd.gamehub.repository.UserNeo4jRepository;
import it.unipi.lsmsd.gamehub.service.IGameNeo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.unipi.lsmsd.gamehub.model.GameNeo4j;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

import static java.util.Arrays.stream;



@Service
public class GameNeo4jService implements IGameNeo4jService {
    @Autowired
    private GameNeo4jRepository gameNeo4jRepository;

    public void removeGame(String name) {
        gameNeo4jRepository.removeGame(name);
    }
    public void addGame(String id, String name, String developers, String categories, String genres){
        gameNeo4jRepository.addGame(id, name, developers, categories, genres);
    }
    public GameNeo4j getGame(String name){
        return gameNeo4jRepository.getGame(name);
    }
    public boolean updateGame(String name, String newName, String newDevelopers, String newCategories, String newGenres){
        gameNeo4jRepository.updateGame(name, newName, newDevelopers, newCategories, newGenres);
        return false;
    }


}