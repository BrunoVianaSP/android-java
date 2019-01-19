package sample.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    static final String BASE_URL = "http://10.0.2.2:3000/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    public void start() {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();

//        UserAPI userAPI = retrofit.create(UserAPI.class);
//        Call<List<User>> call = userAPI.users();
//        call.enqueue(this);
    }

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
//    @Override
//    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//        if(response.isSuccessful()) {
//            List<User> users = response.body();
//            for (User user: users) {
//                System.out.println(user);
//            }
//        } else {
//            System.out.println(response.errorBody());
//        }
//    }
//
//    @Override
//    public void onFailure(Call<List<User>> call, Throwable t) {
//        t.printStackTrace();
//    }
}