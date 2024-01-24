package it.unipi.lsmsd.gamehub.service.impl;

import it.unipi.lsmsd.gamehub.DTO.GameDTO;
import it.unipi.lsmsd.gamehub.DTO.GameWishlistDTO;
import it.unipi.lsmsd.gamehub.model.GameNeo4j;
import it.unipi.lsmsd.gamehub.model.User;
import it.unipi.lsmsd.gamehub.repository.GameNeo4jRepository;
import it.unipi.lsmsd.gamehub.repository.LoginRepository;
import it.unipi.lsmsd.gamehub.repository.UserNeo4jRepository;
import it.unipi.lsmsd.gamehub.service.IGameNeo4jService;
import it.unipi.lsmsd.gamehub.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;
import it.unipi.lsmsd.gamehub.model.Game;
import java.util.stream.Collectors;
import static java.util.Arrays.stream;

@Service
public class GameNeo4jService implements IGameNeo4jService {
    @Autowired
    private GameNeo4jRepository gameNeo4jRepository;
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public GameNeo4j getGameByTitle(String name) {
        return gameNeo4jRepository.findGameByName(name);
    }

    @Override
    public int getGamesIngoingLinks(String name) {
        return gameNeo4jRepository.findGameIngoingLinks(name);
    }
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

    @Override
    public ResponseEntity<List<GameDTO>> getSuggestGames(String userId) {
        try {
            // recupero l ultimo gioco aggiunto in wishlist
            Optional<User> user = loginRepository.findById(userId);
            List<GameWishlistDTO> gameWishlistDTOS = user.get().getGames();
            GameWishlistDTO lastGameWishlistDTO = gameWishlistDTOS.get(gameWishlistDTOS.size() - 1);

            List<GameNeo4j> games = gameNeo4jRepository.findSuggestGames(lastGameWishlistDTO.getId(), userId);
            ModelMapper modelMapper = new ModelMapper();
            List<GameDTO> gameDTOS = games.stream()
                    .map(game -> modelMapper.map(game, GameDTO.class))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(gameDTOS, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Errore durante l accesso al database: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}