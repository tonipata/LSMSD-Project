package it.unipi.lsmsd.gamehub.controller;

import it.unipi.lsmsd.gamehub.model.GameNeo4j;
import it.unipi.lsmsd.gamehub.service.impl.GameNeo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameNeo4jController {

    @Autowired
    private GameNeo4jService gameNeo4jService;
    //add new game
    @PostMapping("/addGame")
    public ResponseEntity<String> addGame(@RequestParam String id, @RequestParam String name, @RequestParam String developers, @RequestParam String categories, @RequestParam String genres) {
        gameNeo4jService.addGame(id, name, developers, categories, genres);
        return ResponseEntity.ok("Game Added");
    }
    // Read a game by name
    @GetMapping("/getGame")
    public ResponseEntity<GameNeo4j> getGame(@RequestParam String name) {
        GameNeo4j game = gameNeo4jService.getGame(name);

        if (game != null) {
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Update a game by name
    @PutMapping("/updateGame")
    public ResponseEntity<String> updateGame(@RequestParam String name,
                                             @RequestParam String newName,
                                             @RequestParam String newDevelopers,
                                             @RequestParam String newCategories,
                                             @RequestParam String newGenres) {
        boolean updated = gameNeo4jService.updateGame(name, newName, newDevelopers, newCategories, newGenres);

        if (updated) {
            return ResponseEntity.ok("Game Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Delete a game by name
    @DeleteMapping("/removeGame")
    public ResponseEntity<String> removeGame(@RequestParam String name) {
        gameNeo4jService.removeGame(name);
        return ResponseEntity.ok("Game Removed");
    }
}