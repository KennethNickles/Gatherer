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
public class SymbolsDeserializerTest {

	@org.junit.Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	Log log;

	@Test
	public void symbols() throws Exception {
		final InputStream inputStream = getResourceAsStream("symbols.json", this);
		final JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
		final Gson gson = new CardGsonFactory().createCardGson();
		final Symbol[] symbols = gson.fromJson(jsonReader, Symbol[].class);
		Assert.assertNotNull(symbols);
		Assert.assertEquals(61, symbols.length);
	}

	private static InputStream getResourceAsStream(String fileName, Object source) {
		try {
			return source.getClass().getResourceAsStream(fileName);
		} catch (Exception e) {
			return null;
		}
	}
}
