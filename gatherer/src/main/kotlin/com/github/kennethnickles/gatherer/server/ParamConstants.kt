package com.github.kennethnickles.gatherer.server

/**
 * @author kenneth.nickles
 * @since 2016-06-04.
 */
class ParamConstants {

    companion object {

        @JvmField
        val PARAM_ADD = "+"
        @JvmField
        val PARAM_OR = "|"
        @JvmField
        val PARAM_ASSIGN = "="
        @JvmField
        val PARAM_AND = "&"
        @JvmField
        val PARAM_FORMAT = "[%s]"
        @JvmField
        val PARAM_EXCLUSIVE = "@(%s)"
        @JvmField
        val NAME_KEY = "name"
        @JvmField
        val RULES_KEY = "rulesText"
        @JvmField
        val EXPANSION_KEY = "expansion"
        @JvmField
        val FORMAT_KEY = "format"
        @JvmField
        val COLOR_KEY = "color"
        @JvmField
        val TYPE_KEY = "type"
        @JvmField
        val SUPERTYPE_KEY = "supertype"
        @JvmField
        val SUBTYPE_KEY = "subtype"
    }
}