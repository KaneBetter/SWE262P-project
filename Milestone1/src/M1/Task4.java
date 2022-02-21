import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Task4 {
    public static void main(String[] args) throws FileNotFoundException {
        // Read json object
        String xmlPath = "./src/doc/books.xml";
        FileReader reader = new FileReader(xmlPath);
        JSONObject jsonObject = XML.toJSONObject(reader);
        // Convert json object into map
        Map<String, Object> map = jsonObject.toMap();
        // Add prefix
        Map<String, Object> newMap = addPrefix(map);
        // Convert map into json object
        JSONObject resJson = new JSONObject(newMap);
        System.out.println(resJson.toString(2));

    }

    private static Map<String, Object> addPrefix(Map<String, Object> map) {
        // {
        //   k1 : {}
        //   k2 : {}
        // }

        // for
        //      o = iterator.next()
        //      o.getvalue() hashmap

        for (Object value : map.values()) {
            if (value instanceof HashMap) {
                // hashmap -> 1.recursion  2.helper to add swe262_
                addPrefix((Map<String, Object>) value);
            } else if (value instanceof ArrayList) {
                // arraylist -> 1.for loop to recursion 2.helper to add swe262_
                ArrayList arrayList = (ArrayList) value;
//                Map<String, Object> newMap = new HashMap<>();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i) instanceof HashMap) {
                        addPrefix((Map<String, Object>) arrayList.get(i));
                    }
                }
            }
        }

        helper(map);
        return map;
    }

    private static void helper(Map<String, Object> map) {
        // create a new map to store the revised entry.
        Map<String, Object> newMap = new HashMap<>();

        for (String key : map.keySet()) {
            String newKey = "swe262_" + key;
            Object value = map.get(key);
            newMap.put(newKey, value);
        }

        map.clear();
        map.putAll(newMap);
    }
}