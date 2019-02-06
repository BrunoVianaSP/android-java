package sample.util;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import sample.model.User;

import static android.content.Context.MODE_PRIVATE;

public class ResourceUtils {


    public static void save(AppCompatActivity ctx, String repositoryKey, String dataSaveKey, Object data) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(repositoryKey, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(dataSaveKey, JsonUtils.json(data));
        editor.apply();
    }

    public static <T> T load(AppCompatActivity ctx, String repositoryKey, String dataSaveKey, Class<T> type) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(repositoryKey, MODE_PRIVATE);
        String json = sharedPreferences.getString(dataSaveKey, "{}");
        Gson g = new Gson();
        T obj = g.fromJson(json, type);
        return obj;
    }

    public static String getBearerToken(AppCompatActivity ctx) {
        User user = load(ctx, ConstantUtils.USER_SHARED_REPOSITORY_KEY, ConstantUtils.USER_SHARED_KEY, User.class);
        return "Bearer " + user.getToken();
    }

    public static User user(AppCompatActivity ctx) {
        User user = ResourceUtils.load(ctx, ConstantUtils.USER_SHARED_REPOSITORY_KEY, ConstantUtils.USER_SHARED_KEY, User.class);
        return user;
    }
}
