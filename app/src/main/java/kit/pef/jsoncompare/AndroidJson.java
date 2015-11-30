package kit.pef.jsoncompare;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class AndroidJson implements TestJson {

    public String getName() {
        return "Android";
    }

    public List<Deer> parsePublicTimeline(InputStream inputStream) {

        List<Deer> result = new ArrayList<Deer>();
        try {       
        	String json = convertStreamToString(inputStream);
            JSONObject jsonObject = new JSONObject(json);
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
            	List<HashMap<String, String>> positions = new ArrayList<HashMap<String,String>>();
            	String idKey = (String) iterator.next();
            	String id = jsonObject.get(idKey).toString();
            	JSONArray deerArray = jsonObject.getJSONArray(idKey);
            	int length = deerArray.length();
            	for (int i = 0; i < length; i++) {
            		HashMap<String, String> pos = new HashMap<String,String>();
            		JSONObject posObject = deerArray.getJSONObject(i);
            		Iterator posIterator = posObject.keys();
            		while(posIterator.hasNext()) {
            			String key = (String) posIterator.next();
            			String value = (String) posObject.get(key);
            			pos.put(key, value);
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
 
    private static String convertStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = new BufferedInputStream(inputStream);
        byte[] buffer = new byte[1024];
        int n = 0;
        try {
            while (-1 != (n = in.read(buffer))) {
                out.write(buffer, 0, n);
            }
        } finally {
            out.close();
            in.close();
        }
        return out.toString("UTF-8");
    }

}
