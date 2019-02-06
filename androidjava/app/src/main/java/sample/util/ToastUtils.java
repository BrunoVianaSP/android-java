package sample.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void shortToast(Context context, CharSequence text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
