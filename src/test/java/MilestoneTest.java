import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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


    /**
     * Milestone5 testcases
     */

    @Test
    public void testAsyncJSONKeyTransform() throws Exception {
        File file = new File("./src/books.xml");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        Function<String, String> func = x -> "swe_262P"+x;
        Future<JSONObject> futureObject	= XML.toJSONObjectM5(br, func);
        System.out.println("Now we have the Future JSONObject.");

        while(!futureObject.isDone()) {
            Thread.sleep(1);
            System.out.println("        Waiting for available JSONObject.");
        }
        JSONObject obj = futureObject.get();
        System.out.println("Now we have the JSONObject.");
        System.out.println(obj.toString(2));
    }

    @Test
    public void testAsyncJSON() throws Exception {
        File file = new File("./src/books.xml");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        Function<String, String> func = x -> "swe_262P"+x;
        Future<JSONObject> futureObject	= XML.toJSONObjectM5(br);
        System.out.println("Now we have the Future JSONObject.");

        while(!futureObject.isDone()) {
            Thread.sleep(1);
            System.out.println("        Waiting for available JSONObject.");
        }
        JSONObject obj = futureObject.get();
        System.out.println("Now we have the JSONObject.");
        System.out.println(obj.toString(2));
    }

    @Test
    public void testAsyncJSONKeyTransformWithJSONException() throws Exception {
        File file = new File("./src/books1.xml");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        Function<String, String> func = x -> "swe_262P"+x;
        Future<JSONObject> futureObject	= XML.toJSONObjectM5(br, func);
        System.out.println("Now we have the Future JSONObject.");
        try {
            while(!futureObject.isDone()) {
                Thread.sleep(1);
                System.out.println("        Waiting for available JSONObject.");
            }
            JSONObject obj = futureObject.get();
            System.out.println("Now we have the JSONObject.");
            System.out.println(obj.toString(2));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testAsyncJSONWithWriter() throws Exception {
        File file = new File("./src/books.xml");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        Future<JSONObject> futureObject	= XML.toJSONObjectM5(br);
        System.out.println("Now we have the Future JSONObject.");
        try {
            while(!futureObject.isDone()) {
                Thread.sleep(1);
                System.out.println("        Waiting for available JSONObject.");
            }
            JSONObject obj = futureObject.get();
            Writer writer = new FileWriter("./src/book.json");
            obj.write(writer);
            writer.close();
            System.out.println("Now we have the JSONObject.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testJSONObjectAsync() throws IOException, ExecutionException, InterruptedException {
        BufferedReader br = new BufferedReader(new FileReader("./src/books.xml"));

        Function<JSONObject, String> f1 = (JSONObject jo) -> {
            System.out.println("Start writing!");
            try {
                Writer writer = new FileWriter("./src/book.json");
                jo.write(writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };

        Function<Exception, String> exp = (Exception e) -> {System.out.println(e); return null;};
        XML.toJSONObjectAsync(br, f1, exp);
        System.out.println("Wait process to finish, Non blocking");
        Thread.sleep(1000);
    }

    @Test
    public void testJSONObjectAsyncWithTwo() throws IOException, ExecutionException, InterruptedException {
        BufferedReader br1 = new BufferedReader(new FileReader("./src/books.xml"));
        BufferedReader br2 = new BufferedReader(new FileReader("./src/books.xml"));
        Function<JSONObject, String> f1 = (JSONObject jo) -> {
            System.out.println("Start writing!");
            try {
                Writer writer = new FileWriter("./src/book1.json");
                jo.write(writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
        Function<JSONObject, String> f2 = (JSONObject jo) -> {
            System.out.println("Start writing!");
            try {
                Writer writer = new FileWriter("./src/book2.json");
                jo.write(writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
        Function<Exception, String> exp = (Exception e) -> {System.out.println(e); return null;};
        XML.toJSONObjectAsync(br1, f1, exp);
        XML.toJSONObjectAsync(br2, f2, exp);
        System.out.println("Wait process to finish, Non blocking");
        Thread.sleep(1000);
    }

    @Test
    public void testJSONObjectAsyncWithException() throws IOException, ExecutionException, InterruptedException {
        BufferedReader br1 = new BufferedReader(new FileReader("./src/books1.xml"));

        Function<JSONObject, String> f1 = (JSONObject jo) -> {
            System.out.println("Start writing!");
            try {
                Writer writer = new FileWriter("./src/book1.json");
                jo.write(writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
        Function<Exception, String> exp = (Exception e) -> {System.out.println(e); return null;};
        XML.toJSONObjectAsync(br1, f1, exp);
        System.out.println("Wait process to finish, Non blocking");
        Thread.sleep(1000);
    }
}