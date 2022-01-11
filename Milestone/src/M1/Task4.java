import org.json.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Task4 {
    public static void main(String[] args) throws FileNotFoundException {
        String xmlPath = args[0];
        FileReader reader = new FileReader(xmlPath);
        XMLTokener xmlTokener = new XMLTokener(reader);
        JSONArray jsonArray = JSONML.toJSONArray(xmlTokener);
        addPrefix(jsonArray);
        System.out.println(jsonArray.toString());

    }
    private static void addPrefix(JSONArray jsonArray) {
        if (jsonArray.isEmpty()) {
            return;
        }
        int i = 0;
        while (i < jsonArray.length()) {
            try {
                if (!jsonArray.get(0).toString().contains("swe262_")) {
                    jsonArray.put(0, "swe262_" + jsonArray.get(0).toString());
                }
                addPrefix(jsonArray.getJSONArray(i));
            } catch (Exception e) {
            }
            i++;
        }
        return;

    }
}