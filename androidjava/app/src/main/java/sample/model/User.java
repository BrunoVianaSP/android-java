package sample.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
    private Integer name;
    private Integer username;
    private String email;
    private String password;
    private String application;

    public static User create(String email, String password) {
        User entity = new User();
        entity.email = email;
        entity.password = password;
        return entity;
    }
}


