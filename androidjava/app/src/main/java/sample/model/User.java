package sample.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean agreement;

    public static User create(String email, String password) {
        User entity = new User();
        entity.email = email;
        entity.password = password;
        return entity;
    }
}


