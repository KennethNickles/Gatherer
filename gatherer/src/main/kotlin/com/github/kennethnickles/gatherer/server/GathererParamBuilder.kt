package com.github.kennethnickles.gatherer.server

import java.util.HashMap
import java.util.Locale

/**
 * @author kenneth.nickles
 * @since 2016-06-04.
 */
class GathererParamBuilder : ParamBuilder {

    override fun getParams(request: GathererRequest): Map<String, String> {
        val map: MutableMap<String, String> = HashMap()
        for (entry in request.getParams()) {
            if (ParamConstants.NAME_KEY.equals(entry.key)) {
                map[entry.key] = getNameParam(request.areNamesExclusive(), entry.value)
            } else if (ParamConstants.TYPE_KEY.equals(entry)) {
                map[entry.key] = getTypeParams(request.areTypesExclusive(), entry.value)
            } else if (ParamConstants.SUPERTYPE_KEY.equals(entry)) {
                map[entry.key] = getSuperTypeParams(request.areSupertypesExclusive(), entry.value)
            } else if (ParamConstants.SUBTYPE_KEY.equals(entry)) {
                map[entry.key] = getSubTypeParams(request.areSubtypesExclusive(), entry.value)
            } else if (ParamConstants.COLOR_KEY.equals(entry)) {
                map[entry.key] = getColorParams(request.areColorsExclusive(), entry.value)
            }
        }
        return map
    }

    override fun getNameParam(isExclusive: Boolean, names: Set<String>): String {
        val builder = StringBuilder()
        if (isExclusive) {
            builder.append(java.lang.String.format(Locale.US, ParamConstants.PARAM_EXCLUSIVE, buildNameParams(names)))
        } else {
            builder.append(buildNameParams(names))
        }
        return builder.toString()
    }

    private fun buildNameParams(names: Set<String>): String {
        val builder = StringBuilder()
        for (name in names) {
            builder.append(java.lang.String.format(Locale.US, ParamConstants.PARAM_FORMAT, name)).append(
                    ParamConstants.PARAM_ADD)
        }
        return builder.deleteCharAt(builder.length - 1).toString()
    }

    override fun getTypeParams(isExclusive: Boolean, types: Set<String>): String {
        val builder = StringBuilder()
        if (isExclusive) {
            builder.append(
                    java.lang.String.format(Locale.US, ParamConstants.PARAM_EXCLUSIVE, getGathererTypeParams(types)))
        } else {
            builder.append(getGathererTypeParams(types))
        }
        return builder.toString()
    }

    private fun getGathererTypeParams(types: Set<String>): String {
        val builder = StringBuilder()
        for (name in types) {
            builder.append(java.lang.String.format(Locale.US, ParamConstants.PARAM_FORMAT, name)).append(
                    ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }

    override fun getSuperTypeParams(isExclusive: Boolean, supertypes: Set<String>): String {
        val builder = StringBuilder()
        if (isExclusive) {
            builder.append(java.lang.String.format(Locale.US, ParamConstants.PARAM_EXCLUSIVE,
                                                   getGathererSupertypeParams(supertypes)))
        } else {
            builder.append(getGathererSupertypeParams(supertypes))
        }
        return builder.toString()
    }

    private fun getGathererSupertypeParams(supertypes: Set<String>): String {
        val builder = StringBuilder()
        for (name in supertypes) {
            builder.append(java.lang.String.format(Locale.US, ParamConstants.PARAM_FORMAT, name)).append(
                    ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }

    override fun getSubTypeParams(isExclusive: Boolean, subtypes: Set<String>): String {
        val builder = StringBuilder()
        if (isExclusive) {
            builder.append(java.lang.String.format(Locale.US, ParamConstants.PARAM_EXCLUSIVE,
                                                   getGathererSubtypeParams(subtypes)))
        } else {
            builder.append(getGathererSubtypeParams(subtypes))
        }
        return builder.toString()
    }

    private fun getGathererSubtypeParams(subtypes: Set<String>): String {
        val builder = StringBuilder()
        for (name in subtypes) {
            builder.append(java.lang.String.format(Locale.US, ParamConstants.PARAM_FORMAT, name)).append(
                    ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }

    override fun getColorParams(isExclusive: Boolean, colors: Set<String>): String {
        val builder = StringBuilder()
        if (isExclusive) {
            builder.append(
                    java.lang.String.format(Locale.US, ParamConstants.PARAM_EXCLUSIVE, getGathererColorParams(colors)))
        } else {
            builder.append(getGathererColorParams(colors))
        }
        return builder.toString()
    }

    private fun getGathererColorParams(colors: Set<String>): String {
        val builder = StringBuilder()
        for (color in colors) {
            builder.append(java.lang.String.format(Locale.US, ParamConstants.PARAM_FORMAT, color)).append(
                    ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }
}