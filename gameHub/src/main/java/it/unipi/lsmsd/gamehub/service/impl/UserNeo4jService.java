package it.unipi.lsmsd.gamehub.service.impl;

import it.unipi.lsmsd.gamehub.model.Game;
import it.unipi.lsmsd.gamehub.model.User;
import it.unipi.lsmsd.gamehub.model.GameNeo4j;
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
    public void loadGames() {
        List<Game> games = gameRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<GameNeo4j> graphGames = games.stream().map(Game->modelMapper.map(Game, GameNeo4j.class)).toList();
        gameNeo4jRepository.saveAll(graphGames);
    }

    @Override
    public List<GameNeo4j> getUserWishlist(String username) {
        return userNeo4jRepository.findByUsername(username);
    }

    @Override
    public void addGameToWishlist(String username, String name) {
        userNeo4jRepository.addGameToUser(username,name);
    }

    @Override
    public void deleteGameToWishlist(String username, String name) {
        userNeo4jRepository.deleteGameFromUser(username,name);
    }

    @Override
    public List<UserNeo4j> getFollowedUser(String username) {
        return userNeo4jRepository.findFollowedUsers(username);
    }

    @Override
    public List<UserNeo4j> getFriendsOfFriends(String username) {
        return userNeo4jRepository.findFriendsOfFriends(username);
    }


    @Override
    public void SyncUser() {
        List<User> usersMongo = loginRepository.findAll();
        for(User user : usersMongo) {
            UserNeo4j userNeo4j = convertUser(user);
            userNeo4jRepository.save(userNeo4j);
        }
    }

    private UserNeo4j convertUser(User user) {
        UserNeo4j userNeo4j = new UserNeo4j();
        userNeo4j.setId(user.getId());
        userNeo4j.setUsername(user.getUsername());
        return userNeo4j;
    }
}
