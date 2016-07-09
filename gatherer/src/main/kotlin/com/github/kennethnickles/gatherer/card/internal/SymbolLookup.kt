package com.github.kennethnickles.gatherer.card.internal

import com.github.kennethnickles.gatherer.card.Symbol
import com.github.kennethnickles.gatherer.util.Enums
import java.util.HashMap

/**
 * @author kenneth.nickles
 * @since 2016-06-29.
 */
class SymbolLookup {

	companion object {

		@JvmStatic
		val mSymbolMap = create()

		@JvmStatic
		private fun create(): Map<String, Symbol> {
			val map: MutableMap<String, Symbol> = HashMap(Symbol.values().size)
			map.put("B", Symbol.BLACK)
			map.put("U", Symbol.BLUE)
			map.put("G", Symbol.GREEN)
			map.put("R", Symbol.RED)
			map.put("W", Symbol.WHITE)
			map.put("C", Symbol.COLORLESS)
			map.put("X", Symbol.X)
			map.put("B/R", Symbol.BLACK_OR_RED)
			map.put("B/G", Symbol.BLACK_OR_GREEN)
			map.put("U/B", Symbol.BLUE_OR_BLACK)
			map.put("U/R", Symbol.BLUE_OR_RED)
			map.put("G/W", Symbol.GREEN_OR_WHITE)
			map.put("G/U", Symbol.GREEN_OR_BLUE)
			map.put("R/G", Symbol.RED_OR_GREEN)
			map.put("R/W", Symbol.RED_OR_WHITE)
			map.put("W/U", Symbol.WHITE_OR_BLUE)
			map.put("W/B", Symbol.WHITE_OR_BLACK)
			map.put("2/B", Symbol.TWO_OR_BLACK)
			map.put("2/U", Symbol.TWO_OR_BLUE)
			map.put("2/G", Symbol.TWO_OR_GREEN)
			map.put("2/R", Symbol.TWO_OR_RED)
			map.put("2/W", Symbol.TWO_OR_WHITE)
			map.put("0", Symbol.ZERO)
			map.put("1", Symbol.ONE)
			map.put("2", Symbol.TWO)
			map.put("3", Symbol.THREE)
			map.put("4", Symbol.FOUR)
			map.put("5", Symbol.FIVE)
			map.put("6", Symbol.SIX)
			map.put("7", Symbol.SEVEN)
			map.put("8", Symbol.EIGHT)
			map.put("9", Symbol.NINE)
			map.put("10", Symbol.TEN)
			map.put("11", Symbol.ELEVEN)
			map.put("12", Symbol.TWELVE)
			map.put("13", Symbol.THIRTEEN)
			map.put("14", Symbol.FOURTEEN)
			map.put("15", Symbol.FIFTEEN)
			map.put("16", Symbol.SIXTEEN)
			map.put("17", Symbol.SEVENTEEN)
			map.put("18", Symbol.EIGHTEEN)
			map.put("19", Symbol.NINETEEN)
			map.put("20", Symbol.TWENTY)
			map.put("S", Symbol.SNOW)
			map.put("P", Symbol.PHYREXIAN_MANA)
			map.put("B/P", Symbol.PHYREXIAN_BLACK)
			map.put("U/P", Symbol.PHYREXIAN_BLUE)
			map.put("G/P", Symbol.PHYREXIAN_GREEN)
			map.put("R/P", Symbol.PHYREXIAN_RED)
			map.put("W/P", Symbol.PHYREXIAN_WHITE)
			map.put("T", Symbol.TAP)
			map.put("Q", Symbol.UNTAP)
			// UNHINGED SYMBOLS
			map.put("HB", Symbol.HALF_BLACK)
			map.put("HU", Symbol.HALF_BLUE)
			map.put("HG", Symbol.HALF_GREEN)
			map.put("HR", Symbol.HALF_RED)
			map.put("HW", Symbol.HALF_WHITE)
			map.put("∞", Symbol.INFINITY)
			map.put("½", Symbol.ONE_HALF)
			map.put("100", Symbol.ONE_HUNDRED)
			map.put("1000000", Symbol.ONE_MILLION)
			map.put("Y", Symbol.Y)
			map.put("Z", Symbol.Z)
			Enums.assertFullMappingValues(Symbol::class.java, map)
			return map
		}

		@JvmStatic
		fun lookup(lookup: String): Symbol? {
			if (mSymbolMap.containsKey(lookup)) {
				return mSymbolMap[lookup]
			}
			if (mSymbolMap.containsKey(Enums.sanitize(lookup))) {
				return mSymbolMap[Enums.sanitize(lookup)]
			}
			return null
		}
	}
}
