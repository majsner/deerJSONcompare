package kit.pef.jsoncompare;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SimpleJson implements TestJson {

    public String getName() {
        return "JSON.simple";
    }

    public List<Deer> parsePublicTimeline(InputStream inputStream) {

        List<Deer> result = new ArrayList<Deer>();

        
        try {
        	JSONParser p = new JSONParser();
        	String id;
			Set keys;
			JSONObject jsonObject = (JSONObject) p.parse(new InputStreamReader(inputStream));
			keys = jsonObject.keySet();
			for (Object key: keys) {
				id = (String) key;
				List<HashMap<String, String>> positions = new ArrayList<HashMap<String,String>>();
				JSONArray jsonArray = (JSONArray) jsonObject.get(key);
				int size = jsonArray.size();
				for (int i = 0; i < size; i++) {
					HashMap<String, String> pos = new HashMap<String,String>();
					JSONObject positionJsonObject = (JSONObject) jsonArray.get(i);
					Set posKeys = positionJsonObject.keySet();
					for (Object posKey:posKeys) {
						String hashKey = (String) posKey;
						String hashValue = positionJsonObject.get(posKey).toString();
						pos.put(hashKey, hashValue);
					}
					positions.add(pos);
				}
				result.add(new Deer(id,positions));
			}

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
