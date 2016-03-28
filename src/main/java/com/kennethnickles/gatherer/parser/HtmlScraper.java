package com.kennethnickles.gatherer.parser;

import com.google.common.collect.Lists;
import com.kennethnickles.gatherer.card.Card;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public class HtmlScraper {

    public static ArrayList<Card> parse(String html) {
        final Document document = Jsoup.parse(html);
        final Elements cardElements = document.getElementsByClass("cardItem");
        final ArrayList<Card> cards = Lists.newArrayList();
        for(Element cardElement: cardElements) {
            cards.add(Card.parse(cardElement));
        }
        return cards;
    }
}
