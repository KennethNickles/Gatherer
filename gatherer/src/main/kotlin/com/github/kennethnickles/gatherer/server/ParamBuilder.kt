package com.github.kennethnickles.gatherer.server

/**
 * @author kenneth.nickles
 * @since 2016-06-04.
 */
interface ParamBuilder {
    fun getParams(request: GathererRequest): Map<String, String>
    fun getNameParam(isExclusive: Boolean, names: Set<String>): String
    fun getTypeParams(isExclusive: Boolean, types: Set<String>): String
    fun getSuperTypeParams(isExclusive: Boolean, supertypes: Set<String>): String
    fun getSubTypeParams(isExclusive: Boolean, subtypes: Set<String>): String
    fun getColorParams(isExclusive: Boolean, colors: Set<String>): String
}