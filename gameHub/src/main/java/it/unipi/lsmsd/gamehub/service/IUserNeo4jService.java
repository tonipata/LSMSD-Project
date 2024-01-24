package it.unipi.lsmsd.gamehub.service;

import it.unipi.lsmsd.gamehub.model.UserNeo4j;

public interface IUserNeo4jService {
    public void SyncUser();
    public void loadGames();
    void followUser(String followerUsername, String followedUsername);
    void unfollowUser(String followerUsername, String followedUsername);
    public void likeGame(String username, String name);
    public void dislikeGame(String username, String name);
    public void removeUser(String username);
    public void addUser(String id, String username);
    public UserNeo4j getUser(String username);
    boolean updateUser(String username, String newUsername);
}
