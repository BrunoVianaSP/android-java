package sample.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String token;
    private LocalDateTime expires;
    private boolean agreement;

    public static User create(String email, String password) {
        User entity = new User();
        entity.email = email;
        entity.password = password;
        return entity;
    }

    public static User create() {
        User entity = new User();
        entity.email = "b";
        entity.password = "b";
        return entity;
    }
}


