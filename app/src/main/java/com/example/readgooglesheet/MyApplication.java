package com.example.readgooglesheet;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication applicationInstance;
    public SharedPreferenceManager sharedPreferenceManager;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationInstance = this;
        this.sharedPreferenceManager = new SharedPreferenceManager();
    }

    public static MyApplication getMyApplicationInstance(){
        return applicationInstance;
    }
    public SharedPreferenceManager getSharedPreferences(){
        return this.sharedPreferenceManager;
    }

}