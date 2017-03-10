package com.wldev.expandablecardviewlist;

import android.app.Application;

/**
 * Created by wldev on 3/10/17.
 */

public class MyApplication extends Application{
    private static MyApplication applicationInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationInstance = this;
    }

    public static MyApplication get() {
        return applicationInstance;
    }
}
