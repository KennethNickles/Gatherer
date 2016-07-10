package com.github.kennethnickles.gatherer.card;

import android.util.Log;
import com.github.kennethnickles.gatherer.util.CardGsonFactory;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @author kenneth.nickles
 * @since 2016-04-04.
 */
public class ColorsDeserializerTest {

	@org.junit.Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	Log log;

    @Test
    public void colors() throws Exception {
        final InputStream inputStream = getResourceAsStream("colors.json", this);
        final JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        final Gson gson = new CardGsonFactory().createCardGson();
        final Color[] colors = gson.fromJson(jsonReader, Color[].class);
        Assert.assertNotNull(colors);
        Assert.assertEquals(5, colors.length);
    }

    private static InputStream getResourceAsStream(String fileName, Object source) {
        try {
            return source.getClass().getResourceAsStream(fileName);
        } catch (Exception e) {
            return null;
        }
    }
}
