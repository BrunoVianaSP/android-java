package sample.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import sample.activity.LoadingActivity;
import sample.activity.LoginActivity;

public class ActivityUtils {
    public static void start(Context ctx, Class<?> target) {
        Intent i=new Intent(ctx, target);
        ctx.startActivity(i);
    }
}
