package com.github.kennethnickles.gatherer.demo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author kenneth.nickles
 * @since 2016-04-09.
 */
public class GathererApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
