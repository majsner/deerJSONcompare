package kit.pef.jsoncompare;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JacksonJson implements TestJson {

    private static JsonFactory sJsonFactory = new JsonFactory();

    public String getName() {
        return "Jackson";
    }

    public List<Deer> parsePublicTimeline(InputStream inputStream) {

        List<Deer> result = new ArrayList<Deer>();

        try {
        	String id;
            JsonParser p = sJsonFactory.createParser(inputStream);
            p.nextToken();
            while (p.nextToken() != JsonToken.END_OBJECT) {
            	id = p.getCurrentName();
            	p.nextToken();
            	List<HashMap<String, String>> positions = new ArrayList<HashMap<String,String>>();
            	if (p.getCurrentToken() == JsonToken.START_ARRAY) {
            		while (p.nextToken() != JsonToken.END_ARRAY) {
            			HashMap<String, String> pos = new HashMap<String,String>();
            			while (p.nextToken() != JsonToken.END_OBJECT){
            				String key = p.getCurrentName();
            				p.nextToken();
            				String value = p.getText();
            				pos.put(key, value);
            			}
            			positions.add(pos);
            		}
            	}
            	result.add(new Deer(id,positions));
            }
            p.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
