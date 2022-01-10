import org.json.JSONObject;
import org.json.XML;
import java.io.FileReader;
import java.io.FileWriter;


//Read an XML file into a JSON object, and extract some smaller sub-object inside, given a certain path (use JSONPointer).
//Write that smaller object to disk as a JSON file.
public class Task2 {
    public static void main(String[] args) throws Exception {
        String xmlPath = args[0];
        FileReader reader = new FileReader(xmlPath);
        JSONObject jsonObject = XML.toJSONObject(reader);
        JSONObject rec = (JSONObject) jsonObject.query(args[1]);
        FileWriter fileWriter = new FileWriter(args[0].replaceAll(".xml","-part.json"));
        rec.write(fileWriter);
        fileWriter.close();
    }

}
