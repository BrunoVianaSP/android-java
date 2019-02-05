package sample.util;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewtworkUtils {
    private static final String BASE_URL_SERVER = "https://sheltered-citadel-11390.herokuapp.com";  // PROD
//    private static final String BASE_URL_SERVER = ""; // LOCAL

    public static <T> T createApi(String path, Class<T> type) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        final Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_SERVER + path)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit.create(type);
    }

    @NonNull
    public static String formatBearerToken(String token) {
        return "Bearer " + token;
    }
}
