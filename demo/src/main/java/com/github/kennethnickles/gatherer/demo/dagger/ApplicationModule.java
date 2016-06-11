package com.github.kennethnickles.gatherer.demo.dagger;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author kenneth.nickles
 * @since 2016-06-10.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @Named("Application")
    public Context provideApplicationContext() {
        return application;
    }
}