package com.example.readgooglesheet.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private SharedPreferences getSharedPreferencesInstance() {
        return MyApplication.getMyApplicationInstance().getSharedPreferences("my_data", Context.MODE_PRIVATE);
    }

    public void setData(String key, String data) {
        SharedPreferences.Editor editor = getSharedPreferencesInstance().edit();
        editor.putString(key, data);
        editor.apply();
    }

    public String getData(String key, String data) {
        return getSharedPreferencesInstance().getString(key, data);
    }

    public boolean clearSharedPreference(){
        SharedPreferences.Editor editor = getSharedPreferencesInstance().edit();
        editor.clear();
        return editor.commit();
    }
}
