package com.github.kennethnickles.gatherer.demo

import android.util.Log

/**
 * @author kenneth.nickles
 * @since 2016-06-26.
 */
interface SearchListener {

    fun onSearchOpen()

    fun onSearchClose(): Boolean

    fun onSearch(query: String?): Boolean

    fun onSearchTextChange(newText: String?): Boolean
}