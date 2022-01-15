import org.json.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Task4 {
    public static void main(String[] args) throws FileNotFoundException {
        String xmlPath = args[0];
        FileReader reader = new FileReader(xmlPath);
        JSONObject jsonObject = XML.toJSONObject(reader);
        Map<String, Object> map = jsonObject.toMap();
        Map<String, Object> newMap = addPrefix(map);
        JSONObject resJson = new JSONObject(newMap);
        System.out.println(resJson.toString());

    }
    private static Map<String, Object> addPrefix(Map<String, Object> map) {
        Set<Map.Entry<String, Object>> currentSet = map.entrySet();
        // hashmap -> 1.recursion  2.helper to add swe262_
        if (currentSet.iterator().next().getValue() instanceof HashMap) {
            addPrefix((Map<String, Object>) currentSet.iterator().next().getValue());
            helper(map);
        } else {
            // arraylist -> 1.for loop to recursion 2.helper to add swe262_
            if (currentSet.iterator().next().getValue() instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) currentSet.iterator().next().getValue();
                Map<String, Object> newMap = new HashMap<>();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i) instanceof HashMap) {
                        addPrefix((Map<String, Object>) arrayList.get(i));
                    }
                }
            }
            helper(map);
        }
        return map;
    }

    private static void helper(Map<String, Object> map) {
        // create a new map to store the revised value.
        Map<String, Object> newMap = new HashMap<>();
        // use iterator to avoid current modify map
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String newKey = "swe262_" + entry.getKey();
            Object value = entry.getValue();
            iterator.remove();
            newMap.put(newKey, value);
        }
        map.putAll(newMap);
    }
}