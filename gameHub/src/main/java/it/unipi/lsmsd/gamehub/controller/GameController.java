package it.unipi.lsmsd.gamehub.controller;

import it.unipi.lsmsd.gamehub.DTO.GameDTO;
import it.unipi.lsmsd.gamehub.DTO.GameDTOAggregation;
import it.unipi.lsmsd.gamehub.DTO.GameDTOAggregation2;
import it.unipi.lsmsd.gamehub.model.Game;
import it.unipi.lsmsd.gamehub.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    private IGameService gameService;

    @GetMapping("/game")
    public ResponseEntity<List<Game>> retrieveGamesByParameters(@RequestBody GameDTO gameDTO) {
        List<Game> gameList = gameService.retrieveGamesByParameters(gameDTO);
        if (!gameList.isEmpty()) {
            return ResponseEntity.ok(gameList);
        }
        System.out.println("gamelist empty");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/prova")
    public ResponseEntity<List<GameDTOAggregation>> retrieveAggregateGamesByGenresAndSortByScore() {
        List<GameDTOAggregation> gameList = gameService.retrieveAggregateGamesByGenresAndSortByScore();

        if (!gameList.isEmpty()) {
            return ResponseEntity.ok(gameList);
        }
        System.out.println("gamelist empty");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/prova4")
    public ResponseEntity<List<GameDTOAggregation2>> findAggregation() {
        List<GameDTOAggregation2> gameList = gameService.findAggregation4();

        if (!gameList.isEmpty()) {
            return ResponseEntity.ok(gameList);
        }
        System.out.println("gamelist empty");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<Game>> showGames(@PageableDefault(sort = { "id" }, size = 50) Pageable pageable) {
        Page<Game> gameDTOPage = gameService.getAll(pageable);
        if (pageable.getPageNumber() >= gameDTOPage.getTotalPages()) {
            // La pagina richiesta supera il numero massimo di pagine disponibili
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (gameDTOPage.isEmpty()) {
            // La pagina è vuota
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(gameDTOPage);
    }


}

