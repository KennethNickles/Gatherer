package com.github.kennethnickles.gatherer.server

/**
 * @author kenneth.nickles
 * @since 2016-06-04.
 *
 * Name	Type	Description
 * type	[]string	Any valid card type. Possible values include enchantment and
 * subtype	[]string	Any valid card subtype. Possible values include zombie and tribal.
 * supertype	[]string	Any valid card supertype, such as legendary
 * name	[]string	A fuzzy match on a card's name
 * oracle	[]string	A fuzzy match on a card's Oracle rules text
 * set	[]string	A three letter identifier for a Magic set
 * rarity	[]string	Select cards printed at this rarity. Options are common, uncommon, rare and mythic
 * color	[]string	Select cards of the chosen color
 * multicolor	bool	Only show cards that are multicolored. Legal values are true and false
 * multiverseid	[]string	Select cards of that have at least one edition with the given Multiverse ID
 * m	[]string	Shortcut for Multiverse ID
 * format	[]string	Only show cards allowed in a specific format. Legal values are vintage, legacy, modern, standard, and commander
 * status	[]string	Only show cards with the given status. Legal values are legal, banned or restricted
 */
class ParamConstants {

	companion object {

		@JvmField
		val PARAM_ADD = "+"
		@JvmField
		val PARAM_ASSIGN = "="
		@JvmField
		val PARAM_AND = '&'
		@JvmField
		val NAME_KEY = "name"
		@JvmField
		val ORACLE_KEY = "oracle"
		@JvmField
		val SET_KEY = "set"
		@JvmField
		val RARITY_KEY = "rarity"
		@JvmField
		val STATUS_KEY = "status"
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
