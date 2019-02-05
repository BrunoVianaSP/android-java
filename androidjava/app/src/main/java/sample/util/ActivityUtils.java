package sample.util;

import android.content.Context;
import android.content.Intent;

public class ActivityUtils {
    public static void start(Context ctx, Class<?> target) {
        Intent i=new Intent(ctx, target);
        ctx.startActivity(i);
    }
}
