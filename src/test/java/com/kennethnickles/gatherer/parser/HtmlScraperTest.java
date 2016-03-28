package com.kennethnickles.gatherer.parser;

import com.kennethnickles.gatherer.card.Card;
import com.kennethnickles.gatherer.card.Rarity;
import com.kennethnickles.gatherer.card.Symbol;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author kenneth.nickles
 * @since 2016-03-07.
 */
public class HtmlScraperTest {

    private static final String BASE_URI = "http://gatherer.wizards.com/Pages/Default.aspx";

    @Test
    public void legendaryCards() {
        final String html = getResourceAsStream("legendary.html", this);
        Assert.assertNotNull(html);
        final ArrayList<Card> cards = HtmlScraper.parse(html);
        Assert.assertEquals(100, cards.size());
        Assert.assertEquals("Aboshan, Cephalid Emperor", cards.get(0).getName());
        Assert.assertEquals(3, cards.get(0).getSymbols().size());
        Assert.assertEquals(0, cards.get(0).getRules().get(0).getSymbols().size());
        Assert.assertEquals("Tap an untapped Cephalid you control: Tap target permanent.",
                            cards.get(0).getRules().get(0).getRuleText());
        Assert.assertEquals(Rarity.RARE, cards.get(0).getRarity());
        Assert.assertEquals("Odyssey", cards.get(0).getExpansion());
        Assert.assertEquals(3, cards.get(0).getRules().get(1).getSymbols().size());
        Assert.assertEquals(Symbol.BLUE, cards.get(0).getRules().get(1).getSymbols().get(0));
        Assert.assertEquals(Symbol.BLUE, cards.get(0).getRules().get(1).getSymbols().get(1));
        Assert.assertEquals(Symbol.BLUE, cards.get(0).getRules().get(1).getSymbols().get(2));
        Assert.assertEquals(": Tap all creatures without flying.", cards.get(0).getRules().get(1).getRuleText());
        Assert.assertEquals("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=31821&type=card",
                            cards.get(0).getArtUrl());
    }

    private static String getResourceAsStream(String fileName, Object source) {
        try {
            return Jsoup.parse(source.getClass().getResourceAsStream(fileName), "UTF-8", BASE_URI).html();
        } catch (Exception e) {
            return null;
        }
    }
}
