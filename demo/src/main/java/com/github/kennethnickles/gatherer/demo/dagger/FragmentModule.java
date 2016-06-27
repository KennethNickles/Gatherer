package com.github.kennethnickles.gatherer.demo.dagger;

import com.github.kennethnickles.gatherer.demo.GathererItemFragment;
import com.github.kennethnickles.gatherer.demo.GathererSearchFragment;
import com.github.kennethnickles.gatherer.demo.dagger.scope.FragmentScope;
import dagger.Module;

/**
 * @author kenneth.nickles
 * @since 2016-06-10.
 */
@Module
@FragmentScope
public class FragmentModule {

    public FragmentModule(GathererSearchFragment gathererSearchFragment) {
    }

    public FragmentModule(GathererItemFragment gathererItemFragment) {
    }
}