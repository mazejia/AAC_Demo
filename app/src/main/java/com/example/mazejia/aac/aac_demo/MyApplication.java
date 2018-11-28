package com.example.mazejia.aac.aac_demo;

import android.app.Application;

import com.example.mazejia.aac.aac_demo.data.db.AppDataBaseManager;

/**
 * Created by mazejia on 2018/11/27.
 */

public class MyApplication extends Application {

    private static MyApplication INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        AppDataBaseManager.getInstance().createDB(this);
    }

    public static MyApplication getInstance(){
        return INSTANCE;
    }
}
