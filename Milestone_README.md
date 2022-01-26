# Milestone2

To see what we have change the original repo, to click [GitHub](https://github.com/tonychen257/JSON-java).

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

For the task1, we manage to stop recursive when we find the sub object, we think it save a lot of time.