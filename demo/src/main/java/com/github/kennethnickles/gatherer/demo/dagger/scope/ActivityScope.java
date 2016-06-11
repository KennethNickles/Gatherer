package com.github.kennethnickles.gatherer.demo.dagger.scope;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Marks Activity scoped dependencies
 *
 * @author kenneth.nickles
 * @since 6/7/16
 */
@Scope
@Retention(SOURCE)
public @interface ActivityScope {

}
