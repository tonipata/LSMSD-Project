package it.unipi.lsmsd.gamehub.repository;

import it.unipi.lsmsd.gamehub.model.GameNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GameNeo4jRepository extends Neo4jRepository<GameNeo4j, String> {
    @Query("MATCH (a:GameNeo4j) WHERE a.name = $name DELETE a")
    void removeGame(String name);
    @Query("CREATE (a:GameNeo4j {id: $id, name: $name, developers: $developers, categories: $categories, genres: $genres })")
    void addGame(String id, String name, String developers, String categories, String genres);
    @Query("MATCH (a:GameNeo4j {name: $name}) RETURN a")
    GameNeo4j getGame(String name);
    //@Query("MERGE (a:GameNeo4j {name: $name}) SET a.name = $name")
    @Query("MERGE (a:GameNeo4j {name: $name}) SET a.name = $newName, a.developers = $newDevelopers, a.categories = $newCategories, a.genres = $newGenres")
    void updateGame(String name, String newName, String newDevelopers, String newCategories, String newGenres);
}
