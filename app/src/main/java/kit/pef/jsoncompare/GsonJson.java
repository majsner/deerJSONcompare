package kit.pef.jsoncompare;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.stream.JsonReader;

public class GsonJson implements TestJson {

	public String getName() {
		return "Gson";
	}

	public List<Deer> parsePublicTimeline(InputStream inputStream) {
		
		List<Deer> result = new ArrayList<Deer>();
		
		try {
			String id;
			JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
			reader.beginObject();
			while (reader.hasNext()) {
				id = reader.nextName();
				reader.beginArray();
				List<HashMap<String, String>> positions = new ArrayList<HashMap<String,String>>();
				while (reader.hasNext()) {
					reader.beginObject();
					HashMap<String, String> pos = new HashMap<String,String>();
					while (reader.hasNext()){
						String key = reader.nextName();
						String value = reader.nextString();
						pos.put(key, value);
					}
					positions.add(pos);
					reader.endObject();
				}
				reader.endArray();
				result.add(new Deer(id,positions));
			}
			reader.endObject();
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
