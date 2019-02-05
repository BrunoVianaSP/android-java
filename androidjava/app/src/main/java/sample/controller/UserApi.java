package sample.controller;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import sample.dto.TokenDto;
import sample.model.User;

public interface UserApi {

    @POST("authenticate")
    Call<User> authenticate(@Body User user);

    @POST("register")
    Call<User> newUser(@Body User user);

    @GET("forgot")
    Call<TokenDto> forgot(@Query("email") final String email);

    @PUT("updateMatchTimeline")
    Call<User> updatePassword(@Body User user, @Header("Authorization") String token);
}
