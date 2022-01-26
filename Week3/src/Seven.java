import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Seven {
    public static void main(String[] args) throws IOException {
        HashSet<String> stopWords = new HashSet<String>(List.of(new Scanner(new File("stop_words.txt")).nextLine().split(",")));
        List allWords = List.of(new String(Files.readAllBytes(Paths.get("pride-and-prejudice.txt"))).toLowerCase(Locale.ROOT).split("[^A-Za-z0-9]+"));
        HashSet<String> uniqueWords = new HashSet<>(allWords);
        uniqueWords.removeAll(stopWords);
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : uniqueWords) map.put(word, Collections.frequency(allWords, word));
        List<Map.Entry<String, Integer>> termFrequency = new ArrayList<>(map.entrySet());
        Collections.sort(termFrequency, (o1, o2) -> o2.getValue() - o1.getValue());
        for (int i = 0; i < 25; i++) System.out.println(termFrequency.get(i).toString().replace("=", "  -  "));


    }
}
