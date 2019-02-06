package sample.util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

public class DialogUtils {

    public static AlertDialog create(AppCompatActivity context, int myDialogTheme, String msg, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, myDialogTheme);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Yes", yesListener)
                .setNegativeButton("No", noListener);
        AlertDialog alert = builder.create();
        alert.show();
        return alert;
    }
}
