package com.github.kennethnickles.gatherer.server

import com.github.kennethnickles.gatherer.card.Color
import com.github.kennethnickles.gatherer.card.Format
import com.github.kennethnickles.gatherer.card.Rarity
import com.github.kennethnickles.gatherer.card.Status
import com.github.kennethnickles.gatherer.card.Supertype
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

	fun getSupertypes(): List<String> {
		if (!mState.mParams.containsKey(ParamConstants.SUPERTYPE_KEY)) {
			return emptyList()
		}
		return mState.mParams[ParamConstants.SUPERTYPE_KEY] as List<String>
	}

	fun getNames(): List<String> {
		if (!mState.mParams.containsKey(ParamConstants.NAME_KEY)) {
			return emptyList()
		}
		return mState.mParams[ParamConstants.NAME_KEY] as List<String>
	}

	fun getOracleText(): List<String> {
		if (!mState.mParams.containsKey(ParamConstants.ORACLE_KEY)) {
			return emptyList()
		}
		return mState.mParams[ParamConstants.ORACLE_KEY] as List<String>
	}

	fun getRarities(): List<String> {
		if (!mState.mParams.containsKey(ParamConstants.RARITY_KEY)) {
			return emptyList()
		}
		return mState.mParams[ParamConstants.RARITY_KEY] as List<String>
	}

	fun getSets(): List<String> {
		if (!mState.mParams.containsKey(ParamConstants.SET_KEY)) {
			return emptyList()
		}
		return mState.mParams[ParamConstants.SET_KEY] as List<String>
	}

	fun getColors(): List<String> {
		if (!mState.mParams.containsKey(ParamConstants.COLOR_KEY)) {
			return emptyList()
		}
		return mState.mParams[ParamConstants.COLOR_KEY] as List<String>
	}

	fun getMulticolor(): Boolean? {
		return mState.mMultiColor
	}

	fun getFormats(): List<String> {
		if (!mState.mParams.containsKey(ParamConstants.FORMAT_KEY)) {
			return emptyList()
		}
		return mState.mParams[ParamConstants.FORMAT_KEY] as List<String>
	}

	fun getStatuses(): List<String> {
		if (!mState.mParams.containsKey(ParamConstants.STATUS_KEY)) {
			return emptyList()
		}
		return mState.mParams[ParamConstants.STATUS_KEY] as List<String>
	}

	fun getPage(): Int? {
		return mState.mPage
	}

	class Builder constructor() {

		val mParams: MutableMap<String, MutableList<String>> = HashMap()
		var mMultiColor: Boolean? = null
		var mPage: Int? = null

		fun withType(type: Type): Builder {
			return withType(type.getName())
		}

		fun withType(type: CharSequence): Builder {
			return withType(type.toString())
		}

		fun withType(type: String): Builder {
			if (type.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.TYPE_KEY)) {
				mParams.put(ParamConstants.TYPE_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.TYPE_KEY]!!.contains(type.toLowerCase())) {
				return this
			}
			mParams[ParamConstants.TYPE_KEY]!!.add(type.toLowerCase())
			return this
		}

		fun withoutType(type: Type): Builder {
			return withoutType(type.getName())
		}

		fun withoutType(type: CharSequence): Builder {
			return withoutType(type.toString())
		}

		fun withoutType(type: String): Builder {
			if (type.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.TYPE_KEY)) {
				return this
			}
			mParams[ParamConstants.TYPE_KEY]!!.remove(type.toLowerCase())
			if (mParams[ParamConstants.TYPE_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.TYPE_KEY)
			}
			return this
		}

		fun clearTypes() {
			mParams.remove(ParamConstants.TYPE_KEY)
		}

		fun withSubtype(subtype: CharSequence): Builder {
			return withSubtype(subtype.toString())
		}

		fun withSubtype(subtype: String): Builder {
			if (subtype.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.SUBTYPE_KEY)) {
				mParams.put(ParamConstants.SUBTYPE_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.SUBTYPE_KEY]!!.contains(subtype.toLowerCase())) {
				return this
			}
			mParams[ParamConstants.SUBTYPE_KEY]!!.add(subtype.toLowerCase())
			return this
		}

		fun withoutSubtype(subtype: CharSequence): Builder {
			return withoutSubtype(subtype.toString())
		}

		fun withoutSubtype(subtype: String): Builder {
			if (subtype.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.SUBTYPE_KEY)) {
				return this
			}
			mParams[ParamConstants.SUBTYPE_KEY]!!.remove(subtype.toLowerCase())
			if (mParams[ParamConstants.SUBTYPE_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.SUBTYPE_KEY)
			}
			return this
		}

		fun clearSubtypes() {
			mParams.remove(ParamConstants.SUBTYPE_KEY)
		}

		fun withSupertype(supertype: Supertype): Builder {
			return withSupertype(supertype.getName())
		}

		fun withSupertype(supertype: CharSequence): Builder {
			return withSupertype(supertype.toString())
		}

		fun withSupertype(supertype: String): Builder {
			if (supertype.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.SUPERTYPE_KEY)) {
				mParams.put(ParamConstants.SUPERTYPE_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.SUPERTYPE_KEY]!!.contains(supertype.toLowerCase())) {
				return this
			}
			mParams[ParamConstants.SUPERTYPE_KEY]!!.add(supertype.toLowerCase())
			return this
		}

		fun withoutSupertype(supertype: CharSequence): Builder {
			return withoutSupertype(supertype.toString())
		}

		fun withoutSupertype(supertype: String): Builder {
			if (supertype.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.SUPERTYPE_KEY)) {
				return this
			}
			mParams[ParamConstants.SUPERTYPE_KEY]!!.remove(supertype.toLowerCase())
			if (mParams[ParamConstants.SUPERTYPE_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.SUPERTYPE_KEY)
			}
			return this
		}

		fun clearSupertypes() {
			mParams.remove(ParamConstants.SUPERTYPE_KEY)
		}

		fun withName(name: CharSequence): Builder {
			return withName(name.toString())
		}


		fun withName(name: String): Builder {
			if (name.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.NAME_KEY)) {
				mParams.put(ParamConstants.NAME_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.NAME_KEY]!!.contains(name.toLowerCase())) {
				return this
			}
			this.mParams[ParamConstants.NAME_KEY]!!.add(name.toLowerCase())
			return this
		}

		fun withoutName(name: CharSequence): Builder {
			return withoutName(name.toString())
		}

		fun withoutName(name: String): Builder {
			if (name.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.NAME_KEY)) {
				return this
			}
			mParams[ParamConstants.NAME_KEY]!!.remove(name.toLowerCase())
			if (mParams[ParamConstants.NAME_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.NAME_KEY)
			}
			return this
		}

		fun clearName() {
			mParams.remove(ParamConstants.NAME_KEY)
		}

		fun withOracleText(oracleText: CharSequence): Builder {
			return withOracleText(oracleText.toString())
		}

		fun withOracleText(oracleText: String): Builder {
			if (oracleText.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.ORACLE_KEY)) {
				mParams.put(ParamConstants.ORACLE_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.ORACLE_KEY]!!.contains(oracleText.toLowerCase())) {
				return this
			}
			mParams[ParamConstants.ORACLE_KEY]!!.add(oracleText.toLowerCase())
			return this
		}

		fun withoutOracleText(oracleText: CharSequence): Builder {
			return withoutOracleText(oracleText.toString())
		}

		fun withoutOracleText(oracleText: String): Builder {
			if (oracleText.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.ORACLE_KEY)) {
				return this
			}
			mParams[ParamConstants.ORACLE_KEY]!!.remove(oracleText.toLowerCase())
			if (mParams[ParamConstants.ORACLE_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.ORACLE_KEY)
			}
			return this
		}

		fun clearOracle() {
			mParams.remove(ParamConstants.ORACLE_KEY)
		}

		fun withSet(set: CharSequence): Builder {
			return withSet(set.toString())
		}

		fun withSet(set: String): Builder {
			if (set.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.SET_KEY)) {
				mParams.put(ParamConstants.SET_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.SET_KEY]!!.contains(set.toLowerCase())) {
				return this
			}
			mParams[ParamConstants.SET_KEY]!!.add(set.toLowerCase())
			return this
		}

		fun withoutSet(set: CharSequence): Builder {
			return withoutSet(set.toString())
		}

		fun withoutSet(set: String): Builder {
			if (set.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.SET_KEY)) {
				return this
			}
			mParams[ParamConstants.SET_KEY]!!.remove(set.toLowerCase())
			if (mParams[ParamConstants.SET_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.SET_KEY)
			}
			return this
		}

		fun clearSets() {
			mParams.remove(ParamConstants.SET_KEY)
		}

		fun withRarity(rarity: Rarity): Builder {
			return withRarity(rarity.getName())
		}

		fun withRarity(rarity: CharSequence): Builder {
			return withRarity(rarity.toString())
		}

		fun withRarity(rarity: String): Builder {
			if (rarity.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.RARITY_KEY)) {
				mParams.put(ParamConstants.RARITY_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.RARITY_KEY]!!.contains(rarity.toLowerCase())) {
				return this
			}
			mParams[ParamConstants.RARITY_KEY]!!.add(rarity.toLowerCase())
			return this
		}

		fun withoutRarity(rarity: Rarity): Builder {
			return withoutRarity(rarity.getName())
		}

		fun withoutRarity(rarity: CharSequence): Builder {
			return withoutRarity(rarity.toString())
		}

		fun withoutRarity(rarity: String): Builder {
			if (rarity.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.RARITY_KEY)) {
				return this
			}
			mParams[ParamConstants.RARITY_KEY]!!.remove(rarity.toLowerCase())
			if (mParams[ParamConstants.RARITY_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.RARITY_KEY)
			}
			return this
		}

		fun clearRarities() {
			mParams.remove(ParamConstants.RARITY_KEY)
		}


		fun withColor(color: Color): Builder {
			return withColor(color.getName())
		}

		fun withColor(color: CharSequence): Builder {
			return withColor(color.toString())
		}

		fun withColor(color: String): Builder {
			if (color.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
				mParams.put(ParamConstants.COLOR_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.COLOR_KEY]!!.contains(color.toLowerCase())) {
				return this
			}
			//HACk Deckbrew doesn't recognize colorless
			if (Color.COLORLESS == Color.from(color.toLowerCase())) {
				return this
			}
			mParams[ParamConstants.COLOR_KEY]!!.add(color.toLowerCase())
			return this
		}

		fun withColors(colors: Set<Color>): Builder {
			for (color in colors) {
				withColor(color)
			}
			return this
		}

		fun withoutColor(color: Color): Builder {
			return withoutColor(color.getName())
		}

		fun withoutColor(color: CharSequence): Builder {
			return withoutColor(color.toString())
		}

		fun withoutColor(color: String): Builder {
			if (color.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.COLOR_KEY)) {
				return this
			}
			mParams[ParamConstants.COLOR_KEY]!!.remove(color.toLowerCase())
			if (mParams[ParamConstants.COLOR_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.COLOR_KEY)
			}
			return this
		}

		fun withoutColors(colors: Set<Color>): Builder {
			for (color in colors) {
				withoutColor(color)
			}
			return this
		}

		fun clearColors() {
			mParams.remove(ParamConstants.COLOR_KEY)
		}

		fun withMulticolor(mulitcolor: Boolean): Builder {
			this.mMultiColor = mulitcolor
			return this
		}

		fun clearMulticolor() {
			mMultiColor = false
		}

		fun withFormat(format: Format): Builder {
			return withFormat(format.getName())
		}

		fun withFormat(format: CharSequence): Builder {
			return withFormat(format.toString())
		}

		fun withFormat(format: String): Builder {
			if (format.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.FORMAT_KEY)) {
				mParams.put(ParamConstants.FORMAT_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.FORMAT_KEY]!!.contains(format.toLowerCase())) {
				return this
			}
			mParams[ParamConstants.FORMAT_KEY]!!.add(format.toLowerCase())
			return this
		}

		fun withoutFormat(format: Format): Builder {
			return withoutFormat(format.getName())
		}

		fun withoutFormat(format: CharSequence): Builder {
			return withoutFormat(format.toString())
		}

		fun withoutFormat(format: String): Builder {
			if (format.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.FORMAT_KEY)) {
				return this
			}
			mParams[ParamConstants.FORMAT_KEY]!!.remove(format.toLowerCase())
			if (mParams[ParamConstants.FORMAT_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.FORMAT_KEY)
			}
			return this
		}

		fun clearFormats() {
			mParams.remove(ParamConstants.FORMAT_KEY)
		}

		fun withStatus(status: Status): Builder {
			return withStatus(status.getName())
		}

		fun withStatus(status: CharSequence): Builder {
			return withStatus(status.toString())
		}

		fun withStatus(status: String): Builder {
			if (status.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.STATUS_KEY)) {
				mParams.put(ParamConstants.STATUS_KEY, ArrayList<String>())
			}
			if (mParams[ParamConstants.STATUS_KEY]!!.contains(status.toLowerCase())) {
				return this
			}
			mParams[ParamConstants.STATUS_KEY]!!.add(status.toLowerCase())
			return this
		}

		fun withoutStatus(status: Status): Builder {
			return withoutStatus(status.getName())
		}

		fun withoutStatus(status: CharSequence): Builder {
			return withoutStatus(status.toString())
		}

		fun withoutStatus(status: String): Builder {
			if (status.isEmpty()) {
				return this
			}
			if (!mParams.containsKey(ParamConstants.STATUS_KEY)) {
				return this
			}
			mParams[ParamConstants.STATUS_KEY]!!.remove(status.toLowerCase())
			if (mParams[ParamConstants.STATUS_KEY]!!.isEmpty()) {
				mParams.remove(ParamConstants.STATUS_KEY)
			}
			return this
		}

		fun clearStatuses() {
			mParams.remove(ParamConstants.STATUS_KEY)
		}

		fun withPage(page: Int): Builder {
			if (page <= 0) {
				return this
			}
			mPage = page
			return this
		}

		fun clearPage() {
			mPage = null
		}

		fun build(): GathererRequest {
			return GathererRequest(this)
		}

		fun clear() {
			mParams.clear()
			mMultiColor = null
			mPage = null
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
