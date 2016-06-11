package com.github.kennethnickles.gatherer.demo.dagger;

import com.github.kennethnickles.gatherer.demo.GathererFragment;
import com.github.kennethnickles.gatherer.demo.dagger.scope.FragmentScope;
import dagger.Subcomponent;

/**
 * @author kenneth.nickles
 * @since 2016-06-10.
 */
@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void injectGathererFragment(GathererFragment fragment);
}