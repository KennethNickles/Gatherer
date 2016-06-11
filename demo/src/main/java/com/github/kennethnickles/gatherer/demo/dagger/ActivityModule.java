package com.github.kennethnickles.gatherer.demo.dagger;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.github.kennethnickles.gatherer.demo.dagger.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

/**
 * @author kenneth.nickles
 * @since 2016-06-10.
 */
@Module
@ActivityScope
public class ActivityModule {

    public ActivityModule(@NonNull Activity activity) {
        this.activity = activity;
    }

    private final Activity activity;

    @Provides
    @Named("Activity")
    public Context provideActivityContext() {
        return activity;
    }

    @Provides
    public Activity provideActivity() {
        return activity;
    }
}

