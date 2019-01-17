package sample.dev.model;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private Integer name;
    @SerializedName("username")
    private Integer username;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("application")
    private String application;

    public User() {}

    public User(Integer name, Integer username, String email, String password, String application) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.application = application;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}


