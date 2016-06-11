package com.github.kennethnickles.gatherer.demo.dagger;

import com.github.kennethnickles.gatherer.demo.GathererApp;

import javax.inject.Singleton;

/**
 * @author kenneth.nickles
 * @since 2016-06-10.
 */
@Singleton
public interface ApplicationComponent {

    void injectGathererApp(GathererApp gathererApp);

    ActivityComponent plus(ActivityModule activityModule);
}