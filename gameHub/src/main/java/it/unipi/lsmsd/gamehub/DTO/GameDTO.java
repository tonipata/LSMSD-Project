package it.unipi.lsmsd.gamehub.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class GameDTO {
    private String name;
    private String genres;
    private int avgScore;
}
