package sample.util;

import com.google.gson.GsonBuilder;

public class JsonUtils {
    public static String json(Object object) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(object);
    }
}
