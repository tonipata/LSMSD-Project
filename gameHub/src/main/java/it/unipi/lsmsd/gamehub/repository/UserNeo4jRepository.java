package it.unipi.lsmsd.gamehub.repository;

import it.unipi.lsmsd.gamehub.model.UserNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.neo4j.repository.query.Query;


@Repository
public interface UserNeo4jRepository extends Neo4jRepository<UserNeo4j, String> {
    @Query("MATCH (a:UserNeo4j {username: $followerUsername}), (b:UserNeo4j {username: $followedUsername}) MERGE (a)-[:FOLLOWS]->(b)")
    void followUser(String followerUsername, String followedUsername);
    @Query("MATCH (a:UserNeo4j {username: $followerUsername})-[r:FOLLOWS]->(b:UserNeo4j {username: $followedUsername}) DELETE r")
    void unfollowUser(String followerUsername, String followedUsername);
    // Method to add a like based on username and game ID
    @Query("MATCH (u:UserNeo4j {username: $username}), (g:GameNeo4j {name: $name}) MERGE (u)-[:ADD]->(g)")
    void likeGame(String username, String name);
    // Method to remove a like based on username and game ID
    @Query("MATCH (u:UserNeo4j {username: $username})-[r:ADD]->(g:GameNeo4j {name: $name}) DELETE r")
    void dislikeGame(String username, String name);


    //@Query("MATCH (u:UserNeo4j {username: $username})-[r:LIKES]->(g:GameNeo4j {id: $gameId}) DELETE r")
    @Query("MATCH (a:UserNeo4j) WHERE a.username = $username DELETE a")
    void removeUser(String username);

    //@Query("MATCH (a:UserNeo4j) WHERE a.username = '$username' DELETE a")
    @Query("CREATE (a:UserNeo4j {id: $id, username: $username})")
    void addUser(String id, String username);

    @Query("MATCH (a:UserNeo4j {username: $username}) RETURN a")
    UserNeo4j getUser(String username);


    @Query("MERGE (a:UserNeo4j {username: $username}) SET a.username = $newUsername")
    void updateUser(String username, String newUsername);

}
