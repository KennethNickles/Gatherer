package com.github.kennethnickles.gatherer.demo

/**
 * @author kenneth.nickles
 * @since 2016-05-30.
 */
interface Bindable<T> {

    fun bind(t: T)
    fun unbind()
}