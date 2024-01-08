package it.unipi.lsmsd.gamehub.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "reviews")

public class Review {
    private String reviewID;
    private String title;
    private int userScore;
    private String comment;
    private String userID;
    private List<Like> likes;
}
