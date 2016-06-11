package com.github.kennethnickles.gatherer.server

import com.github.kennethnickles.gatherer.card.Color
import com.github.kennethnickles.gatherer.card.Type
import java.util.HashMap
import java.util.HashSet

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 *
 * TODO: Parameter differences need to be tested such as "Odyssey" and "odyssey"
 */
class GathererRequest private constructor(builder: Builder) {

    private val mState: Builder = builder

    fun areNamesExclusive(): Boolean {
        return mState.mAreNamesExclusive
    }

    fun areTypesExclusive(): Boolean {
        return mState.mAreTypesExclusive
    }

    fun areSupertypesExclusive(): Boolean {
        return mState.mAreSupertypesExclusive
    }

    fun areSubtypesExclusive(): Boolean {
        return mState.mAreSubtypesExclusive
    }

    fun areColorsExclusive(): Boolean {
        return mState.mAreColorsExclusive
    }

    fun getParams(): Map<String, Set<String>> {
        return mState.mParams
    }

    fun getDeckBrewParams(): Map<String, String> {
        return DeckBrewParamBuilder().getParams(this)
    }

    fun getGathererParams(): Map<String, String> {
        return GathererParamBuilder().getParams(this)
    }

    companion object {

        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder constructor() {

        val mParams: MutableMap<String, MutableSet<String>> = HashMap()
        var mAreNamesExclusive: Boolean = false
        var mAreTypesExclusive: Boolean = false
        var mAreSupertypesExclusive: Boolean = false
        var mAreSubtypesExclusive: Boolean = false
        var mAreColorsExclusive: Boolean = false

        fun withName(name: String): Builder {
            if (!mParams.containsKey(ParamConstants.NAME_KEY)) {
                mParams.put(ParamConstants.NAME_KEY, HashSet<String>())
            }
            this.mParams[ParamConstants.NAME_KEY]!!.add(name)
            return this
        }

        fun withRule(rule: String): Builder {
            if (!mParams.containsKey(ParamConstants.RULES_KEY)) {
                mParams.put(ParamConstants.RULES_KEY, HashSet<String>())
            }
            mParams[ParamConstants.RULES_KEY]!!.add(rule)
            return this
        }

        fun withExpansion(expansion: String): Builder {
            if (!mParams.containsKey(ParamConstants.EXPANSION_KEY)) {
                mParams.put(ParamConstants.EXPANSION_KEY, HashSet<String>())
            }
            this.mParams[ParamConstants.EXPANSION_KEY]!!.add(expansion)
            return this
        }

        fun withFormat(format: String): Builder {
            if (!mParams.containsKey(ParamConstants.FORMAT_KEY)) {
                mParams.put(ParamConstants.FORMAT_KEY, HashSet<String>())
            }
            this.mParams[ParamConstants.FORMAT_KEY]!!.add(format)
            return this
        }

        fun withColor(color: String): Builder {
            if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
                mParams.put(ParamConstants.COLOR_KEY, HashSet<String>())
            }
            this.mParams[ParamConstants.COLOR_KEY]!!.add(color)
            return this
        }

        fun withColor(color: Color): Builder {
            if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
                mParams.put(ParamConstants.COLOR_KEY, HashSet<String>())
            }
            this.mParams[ParamConstants.COLOR_KEY]!!.add(color.getName())
            return this
        }

        fun withColors(colors: Set<Color>): Builder {
            if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
                mParams.put(ParamConstants.COLOR_KEY, HashSet<String>())
            }
            for (color in colors) {
                this.mParams[ParamConstants.COLOR_KEY]!!.add(color.getName())
            }
            return this
        }

        fun areColorsExclusive(areColorsExclusive: Boolean): Builder {
            this.mAreColorsExclusive = areColorsExclusive
            return this
        }

        fun withType(type: String): Builder {
            if (!mParams.containsKey(ParamConstants.TYPE_KEY)) {
                mParams.put(ParamConstants.TYPE_KEY, HashSet<String>())
            }
            this.mParams[ParamConstants.TYPE_KEY]!!.add(type)
            return this
        }

        fun withType(type: Type): Builder {
            if (!mParams.containsKey(ParamConstants.TYPE_KEY)) {
                mParams.put(ParamConstants.TYPE_KEY, HashSet<String>())
            }
            this.mParams[ParamConstants.TYPE_KEY]!!.add(type.name)
            return this
        }

        fun withSubtype(subtype: String): Builder {
            if (!mParams.containsKey(ParamConstants.SUBTYPE_KEY)) {
                mParams.put(ParamConstants.SUBTYPE_KEY, HashSet<String>())
            }
            mParams[ParamConstants.SUBTYPE_KEY]!!.add(subtype)
            return this
        }

        fun build(): GathererRequest {
            return GathererRequest(this)
        }
    }
}
