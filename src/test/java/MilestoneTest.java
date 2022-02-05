import java.io.*;
import java.util.function.Function;

import org.json.JSONException;
import org.json.JSONPointer;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

public class MilestoneTest {
    // this main function is test for milestone2
    public static void main(String[] args) {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<contact>\n"+
                "  <nick>Crista </nick>\n"+
                "  <name>Crista Lopes</name>\n" +
                "  <address>\n" +
                "    <street>Ave of Nowhere</street>\n" +
                "    <zipcode>92611</zipcode>\n" +
                "  </address>\n" +
                "</contact>";
        try {
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), new JSONPointer("/contact/address"));
            System.out.println(jobj);
        } catch (JSONException e) {
            System.out.println(e);
        }

        System.out.println("-----------------------");

        try {
            JSONObject replacement = XML.toJSONObject("<street>Ave of the Arts</street>\n");
            System.out.println("Given replacement: " + replacement);
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlString), new JSONPointer("/contact/address/street"), replacement);
            System.out.println(jobj);
        } catch (JSONException e) {
            System.out.println(e);
        }

        System.out.println("-----------------------");

        String xmlStringArray = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<employee>\n" +
                "<contact>\n"+
                "  <nick>Crista </nick>\n"+
                "  <name>Crista Lopes</name>\n" +
                "  <address>\n" +
                "    <street>Ave of One</street>\n" +
                "    <street>Ave of Two</street>\n" +
                "    <street>Ave of Three</street>\n" +
                "    <zipcode>92611</zipcode>\n" +
                "  </address>\n" +
                "</contact>" +
                "<contact>\n"+
                "  <nick>KC </nick>\n"+
                "  <name>Chen</name>\n" +
                "  <address>\n" +
                "    <street>Irvine</street>\n" +
                "    <zipcode>92614</zipcode>\n" +
                "  </address>\n" +
                "</contact>\n" +
                "</employee>";
        try {
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlStringArray), new JSONPointer("/employee/contact/1"));
            System.out.println(jobj);
        } catch (JSONException e) {
            System.out.println(e);
        }

        System.out.println("-----------------------");

        try {
            JSONObject replacement = XML.toJSONObject("<street>Ave of the Arts</street>\n");
            System.out.println("Given replacement: " + replacement);
            JSONObject jobj = XML.toJSONObject(new StringReader(xmlStringArray), new JSONPointer("/contact/address/street/2"), replacement);
            System.out.println(jobj);
        } catch (JSONException e) {
            System.out.println(e);
        }
    }


    //for milestone3 we begin to use junit as our test tool

    @Test
    public void testMileStone3Fun1() throws FileNotFoundException {
        File file = new File("./src/books.xml");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        Function<String, String> func = x -> "swe_262P_"+x;

        JSONObject jobj = XML.toJSONObject(br,func);
        System.out.println(jobj.toString(2));
    }
    @Test
    public void testMileStone3Fun2() throws FileNotFoundException {
        File file = new File("./src/books.xml");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        Function<String, String> func = x -> new StringBuilder(x).reverse().toString();

        JSONObject jobj = XML.toJSONObject(br,func);
        System.out.println(jobj.toString(2));
    }
}