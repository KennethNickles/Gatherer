package com.github.kennethnickles.gatherer.demo.dagger;

import com.github.kennethnickles.gatherer.demo.GathererActivity;
import com.github.kennethnickles.gatherer.demo.dagger.scope.ActivityScope;
import dagger.Subcomponent;

/**
 * @author kenneth.nickles
 * @since 2016-06-10.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void injectGathererActivity(GathererActivity gathererActivity);

    FragmentComponent plus(FragmentModule fragmentModule);
}