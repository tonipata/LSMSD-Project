package it.unipi.lsmsd.gamehub.service.impl;

import it.unipi.lsmsd.gamehub.DTO.GameDTO;
import it.unipi.lsmsd.gamehub.model.Game;
import it.unipi.lsmsd.gamehub.repository.GameRepository;
import it.unipi.lsmsd.gamehub.service.IGameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService {
    @Autowired
    private GameRepository gameRepository;
    @Override
    public Page<GameDTO> getAll(Pageable pageable) {
        Page<Game> games =  gameRepository.findAll(pageable);
        ModelMapper modelMapper = new ModelMapper();
        return games.map(game -> modelMapper.map(game, GameDTO.class));
    }
    @Override
    public GameDTO createGame(GameDTO gameDTO) {
        // converto il dto in model entity
        ModelMapper modelMapper = new ModelMapper();
        Game game = modelMapper.map(gameDTO, Game.class);
        // inserisco il model nel db
        Game saved = gameRepository.save(game);
        // controllare errori

        // mappare model in dto
        GameDTO gameInserted = modelMapper.map(saved, GameDTO.class);
        return gameInserted;
    }
    @Override
    public void deleteGame(String id) {
        // aggiungere logica di errore
        gameRepository.deleteById(id);
    }
}
