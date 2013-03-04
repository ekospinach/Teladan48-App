package com.ridwanadit.teladan48.postparser;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONPostParser {
	//feed is used as the POJO definition
	//mapper is the Mapper from Json input into POJO
	Post post = new Post();
	ObjectMapper mapper = new ObjectMapper();

	public Post PostParse(String url){
		try {	
			//Configure mapper so that it ignores "Unknown Properties Error"
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			//Parse the JSONFeed from URL using the structure defined in JSONFeed.class
			post = mapper.readValue(new URL(url), Post.class);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
	}
}
