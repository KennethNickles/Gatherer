package com.github.kennethnickles.gatherer.demo.dagger;

import com.github.kennethnickles.gatherer.DeckBrewClient;
import com.github.kennethnickles.gatherer.GathererClient;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * @author kenneth.nickles
 * @since 2016-06-10.
 */
@Module
public class ServerClientModule {

    @Provides
    @Singleton
    public DeckBrewClient provideDeckBrewClient() {
        return new DeckBrewClient();
    }

    @Provides
    @Singleton
    public GathererClient provideGathererClient() {
        return new GathererClient();
    }
}