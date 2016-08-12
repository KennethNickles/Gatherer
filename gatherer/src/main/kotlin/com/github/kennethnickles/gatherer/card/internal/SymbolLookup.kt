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
			map.put("b", Symbol.BLACK)
			map.put("u", Symbol.BLUE)
			map.put("g", Symbol.GREEN)
			map.put("r", Symbol.RED)
			map.put("w", Symbol.WHITE)
			map.put("c", Symbol.COLORLESS)
			map.put("x", Symbol.X)
			map.put("b/r", Symbol.BLACK_OR_RED)
			map.put("b/g", Symbol.BLACK_OR_GREEN)
			map.put("u/b", Symbol.BLUE_OR_BLACK)
			map.put("u/r", Symbol.BLUE_OR_RED)
			map.put("g/w", Symbol.GREEN_OR_WHITE)
			map.put("g/u", Symbol.GREEN_OR_BLUE)
			map.put("r/g", Symbol.RED_OR_GREEN)
			map.put("r/w", Symbol.RED_OR_WHITE)
			map.put("w/u", Symbol.WHITE_OR_BLUE)
			map.put("w/b", Symbol.WHITE_OR_BLACK)
			map.put("2/b", Symbol.TWO_OR_BLACK)
			map.put("2/u", Symbol.TWO_OR_BLUE)
			map.put("2/g", Symbol.TWO_OR_GREEN)
			map.put("2/r", Symbol.TWO_OR_RED)
			map.put("2/w", Symbol.TWO_OR_WHITE)
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
			map.put("s", Symbol.SNOW)
			map.put("o", Symbol.PHYREXIAN_MANA)
			map.put("b/p", Symbol.PHYREXIAN_BLACK)
			map.put("u/p", Symbol.PHYREXIAN_BLUE)
			map.put("g/p", Symbol.PHYREXIAN_GREEN)
			map.put("r/p", Symbol.PHYREXIAN_RED)
			map.put("w/p", Symbol.PHYREXIAN_WHITE)
			map.put("t", Symbol.TAP)
			map.put("q", Symbol.UNTAP)
			// UNHINGED SYMBOLS
			map.put("hb", Symbol.HALF_BLACK)
			map.put("hu", Symbol.HALF_BLUE)
			map.put("hg", Symbol.HALF_GREEN)
			map.put("hr", Symbol.HALF_RED)
			map.put("hw", Symbol.HALF_WHITE)
			map.put("∞", Symbol.INFINITY)
			map.put("½", Symbol.ONE_HALF)
			map.put("100", Symbol.ONE_HUNDRED)
			map.put("1000000", Symbol.ONE_MILLION)
			map.put("y", Symbol.Y)
			map.put("z", Symbol.Z)
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
