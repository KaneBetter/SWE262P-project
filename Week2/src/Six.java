import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Six {
    public static HashSet<String> readStopFile(String filePath) throws FileNotFoundException {
        HashSet<String> stopWords = new HashSet<>();
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
        return stopWords;
    }

    public static HashMap<String, Integer> readDataFile(String filePath, HashSet<String> stopWords) throws FileNotFoundException {
        HashMap<String, Integer> wordFrequency = new HashMap<>();
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
        return wordFrequency;
    }

    public static void sortWord(HashMap<String, Integer> wordFrequency) {
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
        sortWord(readDataFile("pride-and-prejudice.txt", readStopFile("stop_words.txt")));
    }
}
