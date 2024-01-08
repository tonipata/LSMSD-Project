package it.unipi.lsmsd.gamehub.controller;

import io.swagger.annotations.ApiResponse;
import it.unipi.lsmsd.gamehub.DTO.GameDTO;
import it.unipi.lsmsd.gamehub.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class GameController {
    @Autowired
    private IGameService gameService;
    @GetMapping("/getAll")
    public ResponseEntity<Page<GameDTO>> showGames(@PageableDefault(sort = { "id" }, size = 50) Pageable pageable) {
        Page<GameDTO> gameDTOPage = gameService.getAll(pageable);
        if (pageable.getPageNumber() >= gameDTOPage.getTotalPages()) {
            // La pagina richiesta supera il numero massimo di pagine disponibili
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            // Oppure personalizza un messaggio di errore o una risposta appropriata
        }
        return ResponseEntity.ok(gameDTOPage);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createGame(@RequestBody GameDTO gameDTO) {
        // controllare i dati che vengono passati

        GameDTO game = gameService.createGame(gameDTO);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable String id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok().build();
    }
}
