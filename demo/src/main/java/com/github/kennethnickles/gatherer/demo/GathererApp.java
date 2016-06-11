package com.github.kennethnickles.gatherer.demo;

import android.app.Application;
import android.support.annotation.NonNull;
import com.github.kennethnickles.gatherer.demo.dagger.ApplicationComponent;
import com.github.kennethnickles.gatherer.demo.dagger.ApplicationModule;
import com.github.kennethnickles.gatherer.demo.dagger.DaggerGathererApplicationComponent;

/**
 * @author kenneth.nickles
 * @since 2016-06-10.
 */
public class GathererApp extends Application {

    private ApplicationComponent applicationComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();

        final ApplicationModule module = new ApplicationModule(this);
        applicationComponent = DaggerGathererApplicationComponent.builder().applicationModule(module).build();
        applicationComponent.injectGathererApp(this);
    }

    @Override
    public void onTerminate() {
        applicationComponent = null;

        super.onTerminate();
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
