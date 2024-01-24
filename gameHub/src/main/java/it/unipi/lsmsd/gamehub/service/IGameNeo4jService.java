package it.unipi.lsmsd.gamehub.service;
import it.unipi.lsmsd.gamehub.model.GameNeo4j;


public interface IGameNeo4jService {

    public void removeGame(String name);
    public void addGame(String id, String name, String developers, String categories, String genres);
    public GameNeo4j getGame(String name);
    boolean updateGame(String name, String newName, String newDevelopers, String newCategories, String newGenres);
}