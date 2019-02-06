package sample.controller;

import android.support.v7.app.AppCompatActivity;

public class Controller {
    private final AppCompatActivity context;

    protected Controller(AppCompatActivity context) {
        this.context = context;
    }

    protected AppCompatActivity getContext() {
        return context;
    }

}