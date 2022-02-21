import java.io.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.json.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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

    @Test
    public void testMileStone4() {
        JSONObject obj = XML.toJSONObject("<Books><book><title>AAA</title><author>ASmith</author></book><book><title>BBB</title><author>BSmith</author></book></Books>");
        //test the stream content
        List<JSONNode> jsonNodeStream = obj.toStream().collect(Collectors.toList());
        assertEquals(jsonNodeStream.size(), 8);
        for (int i = 0; i < jsonNodeStream.size(); i++) {
            System.out.println(jsonNodeStream.get(i));
        }
        System.out.println("------------------");

        //obj.toStream().forEach(node -> do some transformation, possibly based on the path of the node);
        obj.toStream().forEach(node -> {
            if (node.path.contains("title")) {
                System.out.println(node.toString());
            }
        });
        System.out.println("------------------");

        //List<String> titles = obj.toStream().map(node -> extract value for key "title").collect(Collectors.toList());
        List<String> titles = obj.toStream().filter(node -> node.path.contains("title"))
                        .map(node -> node.value.toString())
                        .collect(Collectors.toList());
        assertEquals(titles.size(), 2);
        System.out.println(titles.get(0));
        System.out.println(titles.get(1));
        System.out.println("------------------");

        //obj.toStream().filter(node -> node with certain properties).forEach(node -> do some transformation);
        obj.toStream().filter(node -> node.key.equals("author"))
                .forEach(node -> {
                    System.out.println(node.value.toString().replace("Smith", "Sean"));
                });
    }
}