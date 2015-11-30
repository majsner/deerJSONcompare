package kit.pef.jsoncompare;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class SmartJson implements TestJson {

	public String getName() {
		return "JSON.smart";
	}

	public List<Deer> parsePublicTimeline(InputStream inputStream) {

		List<Deer> result = new ArrayList<Deer>();

		try {
			JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
			String id;
			Set keys;
			JSONObject jsonObject = (JSONObject) p.parse(inputStream);
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
