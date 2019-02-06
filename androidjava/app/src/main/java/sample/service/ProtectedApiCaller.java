package sample.service;

import lombok.Builder;
import retrofit2.Callback;
import sample.model.User;

@Builder
public class ProtectedApiCaller<T> {
    private User user;
    private Callback<T> callback;
}
