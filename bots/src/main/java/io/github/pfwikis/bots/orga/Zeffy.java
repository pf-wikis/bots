package io.github.pfwikis.bots.orga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.pfwikis.bots.utils.Jackson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import tools.jackson.core.JacksonException;

public class Zeffy {

	public static <T> List<T> request(Class<T> type, String zeffyToken, String url) throws JacksonException, IOException {
		OkHttpClient client = new OkHttpClient();
		var result = new ArrayList<T>();
		boolean cont = true;
		String next = null;
		while(cont) {
			Request request = new Request.Builder()
				  .url(url+(next==null?"":("&starting_after="+next)))
				  .get()
				  .addHeader("Accept", "application/json")
				  .addHeader("Authorization", "Bearer "+zeffyToken)
				  .build();
			Response<T> resp = Jackson.JSON.readValue(client.newCall(request)
					.execute()
					.body()
					.bytes(), Jackson.JSON.getTypeFactory().constructParametricType(Response.class, type));
			result.addAll(resp.data);
			cont = resp.has_more;
			next = resp.next_cursor;
		}
		return result;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private record Response<T>(boolean has_more, String next_cursor, List<T> data) {}
}
