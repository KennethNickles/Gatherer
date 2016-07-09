package com.github.kennethnickles.gatherer.server

import com.github.kennethnickles.gatherer.card.Color
import com.github.kennethnickles.gatherer.card.Supertype
import com.github.kennethnickles.gatherer.card.Type
import java.util.HashMap

/**
 * @author kenneth.nickles
 * @since 2016-06-04.
 */
class ParamFactory {

    fun getParams(request: GathererRequest): Map<String, String> {
        val map: MutableMap<String, String> = HashMap()
        for (entry in request.getParams()) {
            if (ParamConstants.NAME_KEY.equals(entry.key)) {
                map[entry.key] = getNameParams(entry.value)
            } else if (ParamConstants.TYPE_KEY.equals(entry.key)) {
                map[entry.key] = getTypeParams(entry.value)
            } else if (ParamConstants.SUPERTYPE_KEY.equals(entry.key)) {
                map[entry.key] = getSuperTypeParams(entry.value)
            } else if (ParamConstants.SUBTYPE_KEY.equals(entry.key)) {
                map[entry.key] = getSubTypeParams(entry.value)
            } else if (ParamConstants.COLOR_KEY.equals(entry.key)) {
                map[entry.key] = getColorParams(entry.value)
            }
        }
        return map
    }

    fun getNameParams(names: List<String>): String {
        val builder = StringBuilder()
        for (name in names) {
            builder.append(ParamConstants.NAME_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(name)
            builder.append(ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        if (names.size > 1) return builder.substring(5, builder.length).toString() else return builder.toString()
    }

    fun getTypeParams(types: List<String>): String {
        val builder = StringBuilder()
        for (type in types) {
            builder.append(ParamConstants.TYPE_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(Type.from(type)!!.getName().toLowerCase())
            builder.append(ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        if (types.size > 1) return builder.substring(5, builder.length).toString() else return builder.toString()
    }

    fun getSuperTypeParams(supertypes: List<String>): String {
        val builder = StringBuilder()
        for (supertype in supertypes) {
            builder.append(ParamConstants.SUPERTYPE_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(Supertype.from(supertype)!!.getName().toLowerCase())
            builder.append(ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        if (supertypes.size > 1) return builder.substring(10, builder.length).toString() else return builder.toString()
    }

    fun getSubTypeParams(subtypes: List<String>): String {
        val builder = StringBuilder()
        for (subtype in subtypes) {
            builder.append(ParamConstants.SUBTYPE_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(subtype.toLowerCase())
            builder.append(ParamConstants.PARAM_ADD)
        }
        builder.deleteCharAt(builder.length - 1)
        if (subtypes.size > 1) return builder.substring(8, builder.length).toString() else return builder.toString()
    }

    fun getColorParams(colors: List<String>): String {
        val builder = StringBuilder()
        for (color in colors) {
            builder.append(ParamConstants.COLOR_KEY)
            builder.append(ParamConstants.PARAM_ASSIGN)
            builder.append(Color.from(color)!!.getName().toLowerCase())
            builder.append(ParamConstants.PARAM_AND)
        }
        builder.deleteCharAt(builder.length - 1)
        if (colors.size > 1) return builder.substring(6, builder.length).toString() else return builder.toString()
    }
}