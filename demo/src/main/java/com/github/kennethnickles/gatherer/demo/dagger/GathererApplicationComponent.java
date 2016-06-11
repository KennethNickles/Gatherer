package com.github.kennethnickles.gatherer.demo.dagger;

import dagger.Component;

import javax.inject.Singleton;

/**
 * @author kenneth.nickles
 * @since 2016-06-10.
 */
@Singleton
@Component(modules = {ApplicationModule.class, ServerClientModule.class})
public interface GathererApplicationComponent extends ApplicationComponent {

}