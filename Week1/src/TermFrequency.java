import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class TermFrequency {
    public static void main(String[] args) throws FileNotFoundException {
        // load stop words
        File file1 = new File("./stop_words.txt");
        Scanner scanner1 = new Scanner(new FileInputStream(file1));
        HashSet<String> stopWords = new HashSet<>();
        while (scanner1.hasNext()) {
            String line = scanner1.nextLine();
            String[] words = line.split(",");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals("")) {
                    continue;
                }
                stopWords.add(words[i]);
            }
        }

        // load content
        File file = new File("./pride-and-prejudice.txt");
        Scanner scanner = new Scanner(new FileInputStream(file));
        HashMap<String, Integer> map = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.split("\\s+|,\\s*|\"|\'|;\\s*|\\.\\s*|\\?\\s*|!\\s*|-\\s*|:\\s*|@|\\[|\\]|\\(|\\)|\\{|\\}|_|\\*|/");
            for (int i = 0; i < words.length; i++) {
                String word = words[i].toLowerCase(Locale.ROOT);
                if (word.equals("")) {
                    continue;
                }
                if (stopWords.contains(word)) {
                    continue;
                }
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        // print the output
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(infoIds, (o1, o2) -> o2.getValue() - o1.getValue());

        for (int i = 0; i < 26; i++) {
            String id = infoIds.get(i).toString();
            System.out.println(id.replace("=", "  -  "));
        }
    }
}
