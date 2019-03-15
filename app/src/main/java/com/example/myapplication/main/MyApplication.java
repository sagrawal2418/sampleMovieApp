package com.example.myapplication.main;

import android.app.Application;

import static java.util.Collections.singletonList;
import static org.koin.java.standalone.KoinJavaStarter.startKoin;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startKoin(singletonList(DiModule.getModules()));
    }
}
