package com.github.kennethnickles.gatherer.server

import java.util.HashMap

/**
 * @author kenneth.nickles
 * @since 2016-06-04.
 */
class DeckBrewParamBuilder : ParamBuilder {

    override fun getParams(request: GathererRequest): Map<String, String> {
        val map: MutableMap<String, String> = HashMap()
        for (entry in request.params) {
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
        for (name in names) {
            builder.append(ParamConstants.NAME_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(name)
            builder.append(ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }

    override fun getTypeParams(isExclusive: Boolean, types: Set<String>): String {
        val builder = StringBuilder()
        for (type in types) {
            builder.append(ParamConstants.TYPE_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(type)
            builder.append(ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }

    override fun getSuperTypeParams(isExclusive: Boolean, supertypes: Set<String>): String {
        val builder = StringBuilder()
        for (supertype in supertypes) {
            builder.append(ParamConstants.SUPERTYPE_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(supertype)
            builder.append(ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }

    override fun getSubTypeParams(isExclusive: Boolean, subtypes: Set<String>): String {
        val builder = StringBuilder()
        for (subtype in subtypes) {
            builder.append(ParamConstants.SUBTYPE_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(subtype)
            builder.append(ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }

    override fun getColorParams(isExclusive: Boolean, colors: Set<String>): String {
        val builder = StringBuilder()
        for (color in colors) {
            builder.append(ParamConstants.COLOR_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(color)
            builder.append(ParamConstants.PARAM_AND)
        }
        builder.deleteCharAt(builder.length - 1)
        return builder.toString()
    }
}