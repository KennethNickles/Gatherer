package com.kennethnickles.gatherer.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author kenneth.nickles
 * @since 2016-04-08.
 */
public class HtmlScraperFactory extends Converter.Factory {

    /**
     *    public static Card parse(@NonNull Element element) {
     Preconditions.checkArgument(element != null, "Element");
     return new Builder().withName(parseName(element))
     .withId(parseId(element))
     .withUrl(parseUrl(element))
     .withStoreUrl(parseStoreUrl(element))
     .withRules(parseRules(element))
     .withFormat(parseFormat(element), true)
     .withTypes(parseTypes(element))
     .withSubtypes(parseSubtypes(element))
     .withSymbols(parseManaSymbols(element))
     .withConvertedManaCost(parseConvertedManaCost(element))
     .withPower(parsePower(element))
     .withToughness(parseToughness(element))
     .withEdition(parseEdition(element))
     .build();
     }

     private static String parseName(Element element) {
     return element.getElementsByClass("cardTitle").first().getElementsByTag("a").text();
     }

     private static String parseId(Element element) {
     return null;
     }

     private static String parseUrl(Element element) {
     return null;
     }

     private static String parseStoreUrl(Element element) {
     return null;
     }

     private static ArrayList<Rule> parseRules(Element element) {
     final Element typeLine = element.getElementsByClass("rulesText").first();
     final Elements ruleElements = typeLine.getElementsByTag("p");
     final ArrayList<Rule> rules = Lists.newArrayList();
     for (Element ruleElement : ruleElements) {
     final ArrayList<Symbol> symbols = Lists.newArrayList();
     for (Element manaSymbol : ruleElement.getElementsByTag("img")) {
     final String alt = manaSymbol.attr("alt");
     symbols.add(Symbol.from(alt));
     }
     rules.add(Rule.builder()
     .withSymbols(symbols)
     .withConvertedManaCost(0)
     .withRuleText(ruleElement.text())
     .build());
     }
     return rules;
     }

     private static String parseExpansion(Element element) {
     final String title = element.getElementsByClass("setVersions")
     .first()
     .getElementsByTag("img")
     .attr("title");
     return title.substring(0, title.indexOf(' '));
     }

     private static String parseFormat(Element element) {
     return null;
     }

     private static ArrayList<Type> parseTypes(Element element) {
     final String typeString = element.getElementsByClass("typeLine").first().text();
     final String[] typesStrings = typeString.substring(0, typeString.length()).split(" ");
     final ArrayList<Type> types = Lists.newArrayList();
     for (String type : typesStrings) {
     if (Chars.compare(type.charAt(0), (char) 8212) == 0) {
     return types;
     }
     final Type temp = Type.from(type);
     if (temp == null) {
     continue;
     }
     types.add(temp);
     }
     return types;
     }

     private static ArrayList<String> parseSubtypes(Element element) {
     final String subtypeString = element.getElementsByClass("typeLine").first().text();
     final String[] subtypeStrings =
     subtypeString.substring(0, subtypeString.length()).split(" ");
     final ArrayList<String> subtypes = Lists.newArrayList();
     boolean isHyphenFound = false;
     for (String subtype : subtypeStrings) {
     if (Chars.compare(subtype.charAt(0), (char) 8212) == 0) {
     isHyphenFound = true;
     continue;
     }
     if (isPowerToughness(subtype)) {
     continue;
     }
     if (isHyphenFound) {
     subtypes.add(subtype);
     }
     }
     return subtypes;
     }

     private static boolean isPowerToughness(String string) {
     if (Chars.compare(string.charAt(0), (char) 40) != 0) {
     return false;
     }
     if (!Character.isDigit(string.charAt(1))) {
     return false;
     }
     if (Chars.compare(string.charAt(2), (char) 47) != 0) {
     return false;
     }
     if (!Character.isDigit(string.charAt(3))) {
     return false;
     }
     if (Chars.compare(string.charAt(4), (char) 41) != 0) {
     return false;
     }
     return true;
     }

     private static ArrayList<Symbol> parseManaSymbols(Element element) {
     final Elements images =
     element.getElementsByClass("manaCost").first().getElementsByTag("img");
     final ArrayList<Symbol> symbols = Lists.newArrayList();
     for (Element image : images) {
     symbols.add(Symbol.from(image.attr("alt")));
     }
     return symbols;
     }

     private static int parseConvertedManaCost(Element element) {
     final String convertedManaCost =
     element.getElementsByClass("convertedManaCost").first().text();
     return Integer.valueOf(convertedManaCost);
     }

     private static int parsePower(Element element) {
     final String typeString = element.getElementsByClass("typeLine").first().text();
     final String[] typesStrings = typeString.substring(0, typeString.length()).split(" ");
     for (String type : typesStrings) {
     if (isPowerToughness(type)) {
     return Integer.valueOf(Character.toString(type.charAt(1)));
     }
     }
     return -1;
     }

     private static int parseToughness(Element element) {
     final String typeString = element.getElementsByClass("typeLine").first().text();
     final String[] typesStrings = typeString.substring(0, typeString.length()).split(" ");
     for (String type : typesStrings) {
     if (isPowerToughness(type)) {
     return Integer.valueOf(Character.toString(type.charAt(3)));
     }
     }
     return -1;
     }

     private static Edition parseEdition(Element element) {
     return null;
     }

     private static String parseFlavorText(Element element) {
     return null;
     }

     private static String parseMark(Element element) {
     return null;
     }

     private static String parseArtist(Element element) {
     return null;
     }

     private static String parseBlock(Element element) {
     return null;
     }

     private static String parseRarity(Element element) {
     final String title = element.getElementsByClass("setVersions")
     .first()
     .getElementsByTag("img")
     .attr("title");
     final int leftParen = title.indexOf('(');
     final int rightParen = title.indexOf(')');
     final String rarity = title.substring(leftParen + 1, rightParen);
     return rarity;
     }

     private static String parseArtUrl(Element element) {
     return "" + element.getElementsByTag("img")
     .first()
     .attr("src")
     .replace("../", "");
     }
     */

    public static HtmlScraperFactory create() {
        return new HtmlScraperFactory();
    }

    private HtmlScraperFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<?, String> stringConverter(Type type,
                                                Annotation[] annotations,
                                                Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}
