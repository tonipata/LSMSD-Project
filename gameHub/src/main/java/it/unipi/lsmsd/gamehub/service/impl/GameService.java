package it.unipi.lsmsd.gamehub.service.impl;

import it.unipi.lsmsd.gamehub.DTO.GameDTO;
import it.unipi.lsmsd.gamehub.DTO.GameDTOAggregation;
import it.unipi.lsmsd.gamehub.DTO.GameDTOAggregation2;
import it.unipi.lsmsd.gamehub.DTO.ReviewDTO;
import it.unipi.lsmsd.gamehub.model.Game;
import it.unipi.lsmsd.gamehub.model.Review;
import it.unipi.lsmsd.gamehub.repository.GameRepository;
import it.unipi.lsmsd.gamehub.repository.MongoDBAggregation.GameRepositoryCustom;
import it.unipi.lsmsd.gamehub.repository.ReviewRepository;
import it.unipi.lsmsd.gamehub.service.IGameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public List<Game> retrieveGamesByParameters(GameDTO gameDTO) {
        try {
            if(gameDTO.getName()!=null){
                return gameRepository.findByName(gameDTO.getName());
            } else if (gameDTO.getGenres() != null && gameDTO.getAvgScore() != 0) {
                // Both score and genres are provided
                return gameRepository.findByGenresAndAvgScoreGreaterThanEqual(gameDTO.getGenres(), gameDTO.getAvgScore());
            } else if (gameDTO.getGenres() != null) {
                // Only genres are provided
                return gameRepository.findByGenres(gameDTO.getGenres());
            } else if (gameDTO.getAvgScore() != 0) {
                // Only score are provided
                return gameRepository.findByAvgScoreGreaterThanEqual(gameDTO.getAvgScore());
            } else {
                // No specific criteria, return null
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<GameDTOAggregation> retrieveAggregateGamesByGenresAndSortByScore() {
        try{
            List<GameDTOAggregation> gameList= gameRepository.findAggregation();
            if(!gameList.isEmpty()){
                return gameList;
            }
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


    }

    @Override
    public List<GameDTOAggregation2> findAggregation4() {
        try {
            return gameRepository.findAggregation4();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Page<GameDTO> getAll(Pageable pageable) {
        try {
            Page<Game> games =  gameRepository.findAll(pageable);
            ModelMapper modelMapper = new ModelMapper();
            return games.map(game -> modelMapper.map(game, GameDTO.class));
        }
        catch (Exception e) {
            System.out.println("Errore durante il recupero dei giochi: " + e.getMessage());
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
    }

    @Override
    public List<Review> updateGameReview(ReviewDTO reviewDTO, int limit) {
        try {


            Pageable pageable = PageRequest.of(0, limit);


            List<Review> top20Reviews = reviewRepository.findByTitleOrderByLikeCountDesc(reviewDTO.getTitle(), pageable);
            System.out.println("stampo top 20 review\n");
            for (int i = 0; i < top20Reviews.size(); i++) {
                System.out.println(top20Reviews.get(i).getComment());
            }

            // Find the corresponding game document
            List<Game> gameList = gameRepository.findByName(reviewDTO.getTitle());
            System.out.println("stampo nome gioco: " + gameList.get(0).getName());

            if (!gameList.isEmpty()) {
                Game game = gameList.get(0);

                List<Review> existingReviews = game.getReviews();
                System.out.println("stampo review esisitenti gioco: " + existingReviews);

                // Initialize existingReviews if it is null
                if (existingReviews == null) {
                    existingReviews = new ArrayList<>();
                } else {
                    existingReviews.clear();  // Clear existing reviews if any
                }

                // Add the new top 20 reviews to the existing reviews
                existingReviews.addAll(top20Reviews);
                System.out.println("stampo review esisitenti gioco dopo averle aggiornate: " + existingReviews);


                // Set the updated reviews list in the game document
                game.setReviews(existingReviews);

                // Save the updated game document
                gameRepository.save(game);
                return existingReviews;
            } else {
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public GameDTO createGame(GameDTO gameDTO) {
        // converto il dto in model entity
        ModelMapper modelMapper = new ModelMapper();
        Game game = modelMapper.map(gameDTO, Game.class);
        // inserisco il model nel db
        try {
            Game saved = gameRepository.save(game);
            // mappare model in dto
            GameDTO gameInserted = modelMapper.map(saved, GameDTO.class);
            return gameInserted;
        }
        catch (Exception e) {
            System.out.println("Errore nella creazione del gioco: " + e.getMessage());
            return null;
        }
    }
    @Override
    public void deleteGame(String id) {
        // aggiungere logica di errore
        gameRepository.deleteById(id);
    }




}
