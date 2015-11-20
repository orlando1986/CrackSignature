package com.catfish.cracksignature.ui;

import android.app.Application;

import com.catfish.cracksignature.Crack;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        Crack.start();
    }
}
