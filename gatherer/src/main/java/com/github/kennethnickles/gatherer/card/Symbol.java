package com.github.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;
import android.util.Log;
import com.github.kennethnickles.gatherer.card.internal.SymbolLookup;
import com.github.kennethnickles.gatherer.util.Enums;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Strings;
import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2015-07-18.
 */
public enum Symbol {
	BLACK("B"),
	BLUE("U"),
	GREEN("G"),
	RED("R"),
	WHITE("W"),
	COLORLESS("C"),
	X("X", "VARIABLE COLORLESS"),
	BLACK_OR_RED("B/R", "BLACK OR RED"),
	BLACK_OR_GREEN("B/G", "BLACK OR GREEN"),
	BLUE_OR_BLACK("U/B", "BLUE OR BLACK"),
	BLUE_OR_RED("U/R", "BLUE OR RED"),
	GREEN_OR_WHITE("G/W", "GREEN OR WHITE"),
	GREEN_OR_BLUE("G/U", "GREEN OR BLUE"),
	RED_OR_GREEN("R/G", "RED OR GREEN"),
	RED_OR_WHITE("R/W", "RED OR WHITE"),
	WHITE_OR_BLUE("W/U", "WHITE OR BLUE"),
	WHITE_OR_BLACK("W/B", "WHITE OR BLACK"),
	TWO_OR_BLACK("2/B", "TWO OR BLACK"),
	TWO_OR_BLUE("2/U", "TWO OR BLUE"),
	TWO_OR_GREEN("2/G", "TWO OR GREEN"),
	TWO_OR_RED("2/R", "TWO OR RED"),
	TWO_OR_WHITE("2/W", "TWO OR WHITE"),
	ZERO("0"),
	ONE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("10"),
	ELEVEN("11"),
	TWELVE("12"),
	THIRTEEN("13"),
	FOURTEEN("14"),
	FIFTEEN("15"),
	SIXTEEN("16"),
	SEVENTEEN("17"),
	EIGHTEEN("18"),
	NINETEEN("19"),
	TWENTY("20"),
	SNOW("S", "SNOW"),
	PHYREXIAN_MANA("P", "PHYREXIAN MANA"),
	PHYREXIAN_BLACK("B/P", "PHYREXIAN BLACK"),
	PHYREXIAN_BLUE("U/P", "PHYREXIAN BLUE"),
	PHYREXIAN_GREEN("G/P", "PHYREXIAN GREEN"),
	PHYREXIAN_RED("R/P", "PHYREXIAN RED"),
	PHYREXIAN_WHITE("W/P", "PHYREXIAN WHITE"),
	TAP("T", "TAP"),
	UNTAP("Q", "UNTAP"),
	// UNHINGED SYMBOLS
	HALF_BLACK("HB", "HALF BLACK"),
	HALF_BLUE("HU", "HALF BLUE"),
	HALF_GREEN("HG", "HALF GREEN"),
	HALF_RED("HR", "HALF RED"),
	HALF_WHITE("HW", "HALF WHITE"),
	INFINITY("∞", "INFINITY"),
	ONE_HALF("½", "ONE HALF"),
	ONE_HUNDRED("100", "ONE HUNDRED"),
	ONE_MILLION("1000000", "ONE MILLION"),
	Y("Y"),
	Z("Z");

	String mSymbol;
	String mName;

	Symbol(String symbol) {
		this.mSymbol = symbol;
		this.mName = name();
	}

	Symbol(String symbol, String name) {
		this.mSymbol = symbol;
		this.mName = name;
	}

	public String getSymbol() {
		return mSymbol;
	}

	public String getName() {
		return mName;
	}

	public int getCmc() {
		switch (this) {
			case BLACK:
			case BLUE:
			case GREEN:
			case RED:
			case WHITE:
			case COLORLESS:
			case BLACK_OR_GREEN:
			case BLACK_OR_RED:
			case BLUE_OR_BLACK:
			case BLUE_OR_RED:
			case GREEN_OR_BLUE:
			case GREEN_OR_WHITE:
			case RED_OR_GREEN:
			case RED_OR_WHITE:
			case WHITE_OR_BLUE:
			case WHITE_OR_BLACK:
			case SNOW:
			case PHYREXIAN_MANA:
			case PHYREXIAN_BLACK:
			case PHYREXIAN_BLUE:
			case PHYREXIAN_GREEN:
			case PHYREXIAN_RED:
			case PHYREXIAN_WHITE:
				return 1;
			case X:
				return 0;
			case TAP:
			case UNTAP:
				return 0;
			case TWO_OR_BLACK:
			case TWO_OR_BLUE:
			case TWO_OR_GREEN:
			case TWO_OR_RED:
			case TWO_OR_WHITE:
				return 2;
			// UNHINGED
			case Y:
			case Z:
				return 0;
			case ONE_HALF:
			case HALF_BLACK:
			case HALF_BLUE:
			case HALF_GREEN:
			case HALF_RED:
			case HALF_WHITE:
				return 1;
			case ONE_HUNDRED:
				return 100;
			case ONE_MILLION:
			case INFINITY:
				return 1000000;
			default:
				return Integer.valueOf(mSymbol);

		}
	}

	@Nullable
	public static Symbol from(String lookup) {
		if (Strings.isNullOrEmpty(lookup)) {
			return null;
		}
		final String sanitized = Enums.sanitize(lookup);
		if (sanitized == null || sanitized.isEmpty()) {
			return null;
		}
		// TODO: Add Name support for lookups
		final Symbol symbol = SymbolLookup.lookup(sanitized);
		if (symbol == null) {
			Log.e(Symbol.class.getSimpleName(), String.format("Missing Symbol: %s", lookup));
		}
		return symbol;
	}

	@Nullable
	public static List<Symbol> from(List<String> lookups) {
		if (lookups == null || lookups.isEmpty()) {
			return Lists.newArrayList();
		}
		final List<Symbol> symbols = Lists.newArrayList();
		for (String lookup : lookups) {
			symbols.add(from(lookup));
		}
		return symbols;
	}
}
