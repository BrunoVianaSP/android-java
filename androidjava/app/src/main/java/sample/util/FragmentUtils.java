package sample.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentUtils {

    public static void replace(AppCompatActivity ctx, Fragment fragment, int container) {
        FragmentManager fragmentManager = ctx.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void add(AppCompatActivity ctx, Fragment fragment, int container) {
        FragmentManager fragmentManager = ctx.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void clearAllFragments(AppCompatActivity ctx) {
        ctx.getSupportFragmentManager().getFragments().clear();
    }
}
