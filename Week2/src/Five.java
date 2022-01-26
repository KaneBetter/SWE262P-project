import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Five {
    static HashSet<String> stopWords = new HashSet<>();
    static HashMap<String, Integer> wordFrequency = new HashMap<>();

    public static void readStopFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(new FileInputStream(file));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.split(",");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals("")) {
                    continue;
                }
                stopWords.add(words[i]);
            }
        }
    }

    public static void readDataFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(new FileInputStream(file));
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
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
    }

    public static void sortWord() {
        // print the output
        // Sort HashMap with value: turn HashMap into List then use Collections.sort to sort the List
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(wordFrequency.entrySet());
        Collections.sort(infoIds, (o1, o2) -> o2.getValue() - o1.getValue());
        for (int i = 0; i < 25; i++) {
            String id = infoIds.get(i).toString();
            System.out.println(id.replace("=", "  -  "));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readStopFile("./stop_words.txt");
        readDataFile(args[0]);
        sortWord();
    }

}
