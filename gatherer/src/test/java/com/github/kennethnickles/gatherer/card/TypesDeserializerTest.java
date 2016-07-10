package com.github.kennethnickles.gatherer.card;

import com.github.kennethnickles.gatherer.util.CardGsonFactory;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author kenneth.nickles
 * @since 2016-04-04.
 */
public class TypesDeserializerTest {

	@Test
	public void types() throws Exception {
		final InputStream inputStream = getResourceAsStream("types.json", this);
		final JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
		final Gson gson = new CardGsonFactory().createCardGson();
		final Type[] types = gson.fromJson(jsonReader, Type[].class);
		Assert.assertNotNull(types);
		Assert.assertEquals(13, types.length);
	}

	private static InputStream getResourceAsStream(String fileName, Object source) {
		try {
			return source.getClass().getResourceAsStream(fileName);
		} catch (Exception e) {
			return null;
		}
	}
}
