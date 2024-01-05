package it.unipi.lsmsd.gamehub.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "users")
public class User {
    @Id
    String id;
    String username;
    String name;
    String surname;
    String password;
    String email;
}
