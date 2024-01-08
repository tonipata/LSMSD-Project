package it.unipi.lsmsd.gamehub.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Like {
    private int count;
    private List<String> usersID;
}
