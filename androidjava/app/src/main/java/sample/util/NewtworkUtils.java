package sample.util;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewtworkUtils {

    protected static java.util.logging.Logger log = java.util.logging.Logger.getLogger(NewtworkUtils.class.getName());

    public static <T> T createApi(String path, Class<T> type) {
        log.info("createApi");
        log.info("BASE: " + ConstantUtils.BASE_URL_SERVER);
        log.info("PATH: " + path);
        log.info("RESULT: " + (ConstantUtils.BASE_URL_SERVER + path) );
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        final Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantUtils.BASE_URL_SERVER + path)
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
