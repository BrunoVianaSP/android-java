package sample.controller;


import retrofit2.Call;
import retrofit2.Callback;
import sample.util.NewtworkUtils;
import sample.dto.TokenDto;
import sample.model.User;

public class UserController {

    protected java.util.logging.Logger log = java.util.logging.Logger.getLogger(getClass().getName());

    private static final String PATH = "/users/";

    private final UserApi userApi = NewtworkUtils.createApi(PATH, UserApi.class);

    public void authenticate(String email, String password, Callback<User> callback) {
        User user = User.create(email, password);
        Call<User> call = userApi.authenticate(user);
        call.enqueue(callback);
    }

    public void create(User user, Callback<User> callback) {
        Call<User> call = userApi.newUser(user);
        call.enqueue(callback);
    }

    public void forgot(String email, Callback<TokenDto> callback) {
        Call<TokenDto> call = userApi.forgot(email);
        call.enqueue(callback);
    }

    public void reset(User user, String token, Callback<User> callback) {
        Call<User> call = userApi.updatePassword(user, NewtworkUtils.formatBearerToken(token));
        call.enqueue(callback);
    }
}
