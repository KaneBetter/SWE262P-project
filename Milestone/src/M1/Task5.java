
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.json.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


//Read an XML file into a JSON object, and extract some smaller sub-object inside, given a certain path (use JSONPointer).
//Write that smaller object to disk as a JSON file.
public class Task5 {
    public static void main(String[] args) throws Exception {
        String xmlPath = args[0];
        FileReader reader = new FileReader(xmlPath);
        JSONObject jsonObject = XML.toJSONObject(reader);
        try {
            JSONObject rec = (JSONObject) jsonObject.query(args[1]); //"/1"
            System.out.println("Here is subset of json you queried:");
            System.out.println(rec.toString() + "\n");
            System.out.println("Construct a new Object with key:");
            Scanner scanner = new Scanner(System.in);
            String key = scanner.next();
            System.out.println("Value:");
            String value = scanner.next();

            rec.clear();
            rec.put(key,value);

            FileWriter fileWriter = new FileWriter(args[0].replaceAll(".xml","-replaced.json"));
            jsonObject.write(fileWriter);
            fileWriter.close();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Path didn't exist.");
            return;
        }
    }
}