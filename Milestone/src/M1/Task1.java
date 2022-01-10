import org.json.JSONObject;
import org.json.XML;
import java.io.FileReader;
import java.io.FileWriter;


//Read an XML file (given as command line argument) into a JSON object and write the JSON object back on disk as a JSON file.
public class Task1 {
    public static void main(String[] args) throws Exception {
        String xmlPath = args[0];
        FileReader reader = new FileReader(xmlPath);
        JSONObject jsonObject = XML.toJSONObject(reader);
        FileWriter writer = new FileWriter(args[0].replaceAll("xml","json"));
        jsonObject.write(writer);
        writer.close();
    }
}