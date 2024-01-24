package it.unipi.lsmsd.gamehub.controller;

import it.unipi.lsmsd.gamehub.model.UserNeo4j;
import it.unipi.lsmsd.gamehub.service.impl.UserNeo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserNeo4jController {
    @Autowired
    private UserNeo4jService userNeo4jService;

    // to load user from mongo to neo4j
    @PostMapping("/sync")
    public ResponseEntity<String> syncUser() {
        userNeo4jService.SyncUser();
        return ResponseEntity.ok("Sincronizzazione completata");
    }
    // to load games from mongo to neo4j
    @PostMapping("/loadgames")
    public ResponseEntity<String> reqGames() {
        userNeo4jService.loadGames();
        return ResponseEntity.ok("Giochi caricati");
    }
    @GetMapping("/wishlist")
    public ResponseEntity<List<GameNeo4j>> getUSerWishlist(@RequestParam String username) {
        List<GameNeo4j> gameList = userNeo4jService.getUserWishlist(username);
        if (!gameList.isEmpty()) {
            return ResponseEntity.ok(gameList);
        }
        System.out.println("gamelist empty");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestParam String followerUsername, @RequestParam String followedUsername) {
        userNeo4jService.followUser(followerUsername, followedUsername);
        return ResponseEntity.ok("Followed successfully");
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(@RequestParam String followerUsername, @RequestParam String followedUsername) {
        userNeo4jService.unfollowUser(followerUsername, followedUsername);
        return ResponseEntity.ok("Unfollowed successfully");
    }
    // Endpoint to like a game
    @PostMapping("/like")
    public ResponseEntity<String> likeGame(@RequestParam String username, @RequestParam String name) {
        userNeo4jService.likeGame(username, name);
        return ResponseEntity.ok("Added successfully");
    }

    // Endpoint to dislike a game
    @DeleteMapping("/dislike")
    public ResponseEntity<String> dislikeGame(@RequestParam String username, @RequestParam String name) {
        userNeo4jService.dislikeGame(username, name);
        return ResponseEntity.ok("Removed successfully");
    }

    //remove user
    @DeleteMapping("/removeUser")
    public ResponseEntity<String> removeUser(@RequestParam String username) {
        userNeo4jService.removeUser(username);
        return ResponseEntity.ok("User Removed");
    }

    //add new user
    @PostMapping ("/addUser")
    public ResponseEntity<String> addUser(@RequestParam String id, @RequestParam String username) {
        userNeo4jService.addUser(id, username);
        return ResponseEntity.ok("User Added");
    }
    //get id and username
    @GetMapping ("/getUser")
    public ResponseEntity<UserNeo4j> getUser(@RequestParam String username) {
        UserNeo4j user = userNeo4jService.getUser(username);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //update username on the basis of old username
    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestParam String username, @RequestParam String newUsername) {
        boolean updated = userNeo4jService.updateUser(username, newUsername);

        if (updated) {
            return ResponseEntity.ok("User Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
