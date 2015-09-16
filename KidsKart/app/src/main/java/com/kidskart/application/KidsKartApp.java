package com.kidskart.application;

import android.app.Application;

/**
 * Created by Nilesh on 16/09/15.
 */
public class KidsKartApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyVolley.init(this);
    }
}
