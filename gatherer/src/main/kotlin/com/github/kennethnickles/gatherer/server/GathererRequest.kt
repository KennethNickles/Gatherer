package com.github.kennethnickles.gatherer.server

import com.github.kennethnickles.gatherer.card.Color
import com.github.kennethnickles.gatherer.card.Type
import java.util.ArrayList
import java.util.HashMap

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 *
 * TODO: Parameter differences need to be tested such as "Odyssey" and "odyssey"
 */
data class GathererRequest private constructor(val builder: Builder) {

    companion object {

        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    private val mState: Builder = builder

    fun getParams(): Map<String, List<String>> {
        return mState.mParams
    }

    fun getNames(): List<String> {
        if (!mState.mParams.containsKey(ParamConstants.NAME_KEY)) {
            return emptyList()
        }
        return mState.mParams[ParamConstants.NAME_KEY] as List<String>
    }

    fun getSupertypes(): List<String> {
        if (!mState.mParams.containsKey(ParamConstants.SUPERTYPE_KEY)) {
            return emptyList()
        }
        return mState.mParams[ParamConstants.SUPERTYPE_KEY] as List<String>
    }

    fun getTypes(): List<String> {
        if (!mState.mParams.containsKey(ParamConstants.TYPE_KEY)) {
            return emptyList()
        }
        return mState.mParams[ParamConstants.TYPE_KEY] as List<String>
    }

    fun getSubtypes(): List<String> {
        if (!mState.mParams.containsKey(ParamConstants.SUBTYPE_KEY)) {
            return emptyList()
        }
        return mState.mParams[ParamConstants.SUBTYPE_KEY] as List<String>
    }

    fun getColors(): List<String> {
        if (!mState.mParams.containsKey(ParamConstants.COLOR_KEY)) {
            return emptyList()
        }
        return mState.mParams[ParamConstants.COLOR_KEY] as List<String>
    }

    class Builder constructor() {

        val mParams: MutableMap<String, MutableList<String>> = HashMap()

        fun withName(name: String): Builder {
            if (!mParams.containsKey(ParamConstants.NAME_KEY)) {
                mParams.put(ParamConstants.NAME_KEY, ArrayList<String>())
            }
            this.mParams[ParamConstants.NAME_KEY]!!.add(name.toLowerCase())
            return this
        }

        fun withRule(rule: String): Builder {
            if (!mParams.containsKey(ParamConstants.RULES_KEY)) {
                mParams.put(ParamConstants.RULES_KEY, ArrayList<String>())
            }
            mParams[ParamConstants.RULES_KEY]!!.add(rule.toLowerCase())
            return this
        }

        fun withExpansion(expansion: String): Builder {
            if (!mParams.containsKey(ParamConstants.EXPANSION_KEY)) {
                mParams.put(ParamConstants.EXPANSION_KEY, ArrayList<String>())
            }
            this.mParams[ParamConstants.EXPANSION_KEY]!!.add(expansion.toLowerCase())
            return this
        }

        fun withFormat(format: String): Builder {
            if (!mParams.containsKey(ParamConstants.FORMAT_KEY)) {
                mParams.put(ParamConstants.FORMAT_KEY, ArrayList<String>())
            }
            this.mParams[ParamConstants.FORMAT_KEY]!!.add(format.toLowerCase())
            return this
        }

        fun withColor(color: String): Builder {
            if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
                mParams.put(ParamConstants.COLOR_KEY, ArrayList<String>())
            }
            this.mParams[ParamConstants.COLOR_KEY]!!.add(color.toLowerCase())
            return this
        }

        fun withColor(color: Color): Builder {
            if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
                mParams.put(ParamConstants.COLOR_KEY, ArrayList<String>())
            }
            this.mParams[ParamConstants.COLOR_KEY]!!.add(color.getName().toLowerCase())
            return this
        }

        fun withColors(colors: Set<Color>): Builder {
            if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
                mParams.put(ParamConstants.COLOR_KEY, ArrayList<String>())
            }
            for (color in colors) {
                this.mParams[ParamConstants.COLOR_KEY]!!.add(color.getName().toLowerCase())
            }
            return this
        }

        fun withType(type: String): Builder {
            if (!mParams.containsKey(ParamConstants.TYPE_KEY)) {
                mParams.put(ParamConstants.TYPE_KEY, ArrayList<String>())
            }
            this.mParams[ParamConstants.TYPE_KEY]!!.add(type.toLowerCase())
            return this
        }

        fun withType(type: Type): Builder {
            if (!mParams.containsKey(ParamConstants.TYPE_KEY)) {
                mParams.put(ParamConstants.TYPE_KEY, ArrayList<String>())
            }
            this.mParams[ParamConstants.TYPE_KEY]!!.add(type.getName().toLowerCase())
            return this
        }

        fun withSubtype(subtype: String): Builder {
            if (!mParams.containsKey(ParamConstants.SUBTYPE_KEY)) {
                mParams.put(ParamConstants.SUBTYPE_KEY, ArrayList<String>())
            }
            mParams[ParamConstants.SUBTYPE_KEY]!!.add(subtype.toLowerCase())
            return this
        }

        fun build(): GathererRequest {
            return GathererRequest(this)
        }

        override fun toString(): String {
            val builder = StringBuilder()
            for (entry in mParams) {
                builder.append(entry.key).append(" -> ")
                for (value in entry.value) {
                    builder.append(value).append(", ")
                }
                builder.delete(builder.length - 2, builder.length - 1)
            }
            return builder.toString()
        }
    }
}
