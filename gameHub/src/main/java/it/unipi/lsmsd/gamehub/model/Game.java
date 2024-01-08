package it.unipi.lsmsd.gamehub.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "games")

public class Game {
    private String id;
    private String name;
    private String genres;
    private String releaseDate;
    private int avgScore;
    private double Price;
    private String About_the_game;
    private String Supported_languages;
    private String Developers;
    private String Publishers;
    private String Categories;
    private URL URL;
    private List<Review> Reviews;

}
