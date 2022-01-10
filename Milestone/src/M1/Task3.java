import org.json.*;
import java.io.FileReader;
import java.io.FileWriter;


//Read an XML file into a JSON object, and extract some smaller sub-object inside, given a certain path (use JSONPointer).
//Write that smaller object to disk as a JSON file.
public class Task3 {
    public static void main(String[] args) throws Exception {
        String xmlPath = args[0];
        FileReader reader = new FileReader(xmlPath);
        JSONObject jsonObject = XML.toJSONObject(reader);
        try{
            JSONObject rec = (JSONObject) jsonObject.query(args[1]);
            FileWriter fileWriter = new FileWriter(args[0].replaceAll(".xml","PathTo.json"));
            rec.write(fileWriter);
            fileWriter.close();
            return;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Path isn't exist.");
            return;
        }
    }

}
