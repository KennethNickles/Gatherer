package com.kennethnickles.gatherer.parser;

import com.kennethnickles.gatherer.card.Card;
import com.kennethnickles.gatherer.card.Symbol;

import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author kenneth.nickles
 * @since 2016-03-07.
 */
@Ignore("needs to be updated")
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
        Assert.assertEquals(3, cards.get(0).getRules().get(1).getSymbols().size());
        Assert.assertEquals(Symbol.blue, cards.get(0).getRules().get(1).getSymbols().get(0));
        Assert.assertEquals(Symbol.blue, cards.get(0).getRules().get(1).getSymbols().get(1));
        Assert.assertEquals(Symbol.blue, cards.get(0).getRules().get(1).getSymbols().get(2));
        Assert.assertEquals(": Tap all creatures without flying.",
                            cards.get(0).getRules().get(1).getRuleText());
    }

    private static String getResourceAsStream(String fileName, Object source) {
        try {
            return Jsoup.parse(source.getClass().getResourceAsStream(fileName), "UTF-8", BASE_URI)
                        .html();
        } catch (Exception e) {
            return null;
        }
    }
}
