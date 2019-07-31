package com.example.chen.gank.app;

import android.app.Application;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/18 11:00
 */
public class GankApp extends Application {
    private static GankApp sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    public static GankApp getApp() {
        return sApp;
    }
}
