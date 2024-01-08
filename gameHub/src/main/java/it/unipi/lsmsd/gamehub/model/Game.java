package it.unipi.lsmsd.gamehub.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "games")
public class Game {
    @Id
    private String id;
    private String name;
    @Field("Release date")
    private String releaseDate;
    private Double price;
    @Field("About the game")
    private String aboutTheGame;
    @Field("Supported languages")
    private String supportedLanguages;
    private String developers;
    private String publishers;
    private String categories;
    private String genres;
    // mettere i voti
}
