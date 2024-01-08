package it.unipi.lsmsd.gamehub.service;

import it.unipi.lsmsd.gamehub.DTO.GameDTO;
import it.unipi.lsmsd.gamehub.model.Game;

import java.util.List;

public interface IGameService {
    public List<Game> retrieveGamesByParameters(GameDTO gameDTO);




}
