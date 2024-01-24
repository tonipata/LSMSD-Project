package it.unipi.lsmsd.gamehub.service.impl;

import it.unipi.lsmsd.gamehub.model.Game;
import it.unipi.lsmsd.gamehub.model.GameNeo4j;
import it.unipi.lsmsd.gamehub.model.User;
import it.unipi.lsmsd.gamehub.model.UserNeo4j;
import it.unipi.lsmsd.gamehub.repository.GameNeo4jRepository;
import it.unipi.lsmsd.gamehub.repository.GameRepository;
import it.unipi.lsmsd.gamehub.repository.LoginRepository;
import it.unipi.lsmsd.gamehub.repository.UserNeo4jRepository;
import it.unipi.lsmsd.gamehub.service.IUserNeo4jService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

import static java.util.Arrays.stream;

@Service
public class UserNeo4jService implements IUserNeo4jService {
    @Autowired
    private UserNeo4jRepository userNeo4jRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameNeo4jRepository gameNeo4jRepository;
    @Override
    public void SyncUser() {
        List<User> usersMongo = loginRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<UserNeo4j> userNeo4js = usersMongo.stream().map(User->modelMapper.map(User, UserNeo4j.class)).toList();
        userNeo4jRepository.saveAll(userNeo4js);
    }
    public void loadGames() {
        List<Game> games = gameRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<GameNeo4j> graphGames = games.stream().map(Game->modelMapper.map(Game, GameNeo4j.class)).toList();
        gameNeo4jRepository.saveAll(graphGames);
    }
    @Override
    public void followUser(String followerUsername, String followedUsername) {
        userNeo4jRepository.followUser(followerUsername, followedUsername);
    }
    @Override
    public void unfollowUser(String followerUsername, String followedUsername) {
        userNeo4jRepository.unfollowUser(followerUsername, followedUsername);
    }
    public void likeGame(String username, String name) {
        // You can add additional business logic here if needed
        userNeo4jRepository.likeGame(username, name);
    }
    public void dislikeGame(String username, String name) {
        // You can add additional business logic here if needed
        userNeo4jRepository.dislikeGame(username, name);
    }
    public void removeUser(String username) {
        userNeo4jRepository.removeUser(username);
    }
    public void addUser(String id, String username){
        userNeo4jRepository.addUser(id, username);
    }
    public UserNeo4j getUser(String username){
        return userNeo4jRepository.getUser(username);
    }
    public boolean updateUser(String username, String newUsername){
        userNeo4jRepository.updateUser(username, newUsername);
        return false;
    }
}
