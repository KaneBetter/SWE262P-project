> Group Member: Kaiqin Chen, Ruiyan Ma

# SWE262P Milestone1

- [Link](https://github.com/tonychen257/SWE262P-project/blob/main/Milestone1/src/M1/README.md)

# SWE262P Milestone2

## Implement thoughts

1. add two more function

   ```java
   public static JSONObject toJSONObject(Reader reader, JSONPointer path) {}
   private static boolean parseMile2(XMLTokener x, JSONObject context, String name, XMLParserConfiguration config, String stopKey) {}
   ```

2. Create two global value to track the process

   ```java
       // global values for milestone2 task1
       static boolean pathFind = false;
       static int reachIndex = -1; // this is for JSONArray, example books/2
   	
   	 // global values for milestone2 task2
   	 static boolean replacePathFind = false;
       static int replaceIndex = -1;
       static boolean hasReplaced = false; //make sure only replace once
   ```

3. Function details can be found in the XML.java
   - Simple idea for Task1 is when we found the close tag for stopWord, we immediately stop the recursion by set `pathFind` to  `True`
   - Simple idea fro Task2 is when we found the replace token's close tag, we give `replacePathFind` to `True`, so that in the next recursion will replace the JSONObject, and we use `hasReplaced` to make sure that replace element only happen once. For this task, we do not break the recursion.

## Unit Test

We create 4 tests.

```bash
# Path: "/contact/address"
{"address":{"zipcode":92611,"street":"Ave of Nowhere"}}
-----------------------
# Path: "/contact/address/street"
Given replacement: {"street":"Ave of the Arts"}
{"contact":{"nick":"Crista","address":{"zipcode":92611,"street":"Ave of the Arts"},"name":"Crista Lopes"}}
-----------------------
# Path: "/employee/contact/1"
{"nick":"KC","address":{"zipcode":92614,"street":"Irvine"},"name":"Chen"}
-----------------------
# Path: "/contact/address/street/2"
# for the last one we have not figure how to fix it with JSONArray, we might do it in the future update
Given replacement: {"street":"Ave of the Arts"}
{"employee":{"contact":[{"nick":"Crista","address":{"zipcode":92611,"street":["Ave of the Arts","Ave of Two","Ave of Three"]},"name":"Crista Lopes"},{"nick":"KC","address":{"zipcode":92614,"street":"Irvine"},"name":"Chen"}]}}
```

## Performance

For the task1, we manage to stop recursive when we find the sub object, we think it save a lot of time. For example, if you try to find the first object in 1000 object, with milestone2 we only need to go the first object and return and with milestone1 we need to recursively go through all the object.

# SWE262P Milestone3

## Implement thoughts

- Add two more function

```java
public static JSONObject toJSONObject(Reader reader, Function keyTransformer) {}
private static boolean parseMile3(XMLTokener x, JSONObject context, String name, XMLParserConfiguration config, Function keyTransformer) {}
```

- `parseMile3` function is built based on the original `parse` function. We just need to apply the `keyTransformer` function every time when `parse	` function try to use `accumulate` method to expand the result. For example, we change the line1 to line2. In this way, we can make sure every `Tag` or `TagName` will be applied by the `keyTransformer` function.

```java
context.accumulate(config.getcDataTagName(), string);
context.accumulate((String) keyTransformer.apply(config.getcDataTagName()), string);
```

## Unit Test

In the milestone3, we began to utilize `Junit` as our test tool.

```java
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
```

We will try to rewrite milestone2's unit test in the future with `Junit` to maintain the consistency.

##  Performance

We think for the milestone3 we did a lot of improvement in the performance. For this mission, we always need to traverse the whole XML. However, in milestone1, we need to do it twice. First we traverse to get the XML to JSON, and then the client need do another traverse to change the key.

In the milestone3, by using of Functions provided by JAVA 8 and the lambda expressions, we made the retrvise only happen once. For detail implementation, we built our function on the top of the original parse function with little adjustments.

# SWE262P Milestone4

## Implement thoughts

We think `JSONObject` as a tree not a `HashMap`. When we want to traverse a tree, we do it recursively

In detail, we have to deal with several questions.

1. What attributes are required for this node?

2. What are the params of the recursive function?

3. When should we add the node into list?

4. What should we do with `JSONArray` Object?

   ...

Step by step, we break the challenge into small pieces to solve the problem by create a new class named `JSONNode` and two new methods in `JSONObject.java` file called `toStream()` and `addAllNodes()`

```java
public Stream<JSONNode> toStream() {}
private List<JSONNode> addAllNodes(String p, JSONObject jb, ArrayList<JSONNode> nodes) {}
```

## Unit Test

We begin to use `assertTrue ` to test our function. We write out test cases in `/test/java/MilestoneTest.java`

```java
public void testMileStone4() {}
```

## Performance

When we turn a `JSONObject` into a `Stream` Object, we open a whole new world for the object. Because once it turned, it can utilize all the APIs in `Stream`, for example, `map`, `filter`, `collector`, `flatmap`, `foreach` etc.