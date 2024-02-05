package it.unipi.lsmsd.gamehub.service;

import it.unipi.lsmsd.gamehub.model.GameNeo4j;
import it.unipi.lsmsd.gamehub.model.UserNeo4j;
import java.util.List;

public interface IUserNeo4jService {
    public void SyncUser();
    public void loadGames();

    //DA MODIFICARE NEL MAIN->TROVA LA LISTA DI GIOCHI DEGLI AMICI
    public List<GameNeo4j> getUserWishlist(String username,String friendUsername);

    public void addGameToWishlist(String username,String name);

    public void deleteGameToWishlist(String username,String name);

    List<UserNeo4j> getFollowedUser(String username);

    List<UserNeo4j> getFriendsOfFriends(String username);

    List<UserNeo4j> getSuggestedFriends(String username);

   // public void addLikeToReview(String username,String id);


    //DA MODIFICARE NEL MAIN->AGGIUNGE LIKE AD UNA REVIEW
    public boolean addLikeToReview(String username,String id);

    //AGGIUNGERE NEL MAIN-> FUNZIONE CHE CONTA IL NUMERO TOTALE DI UTENTI
    public long countUserDocument();



}