import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Eight {
    public static void main(String[] args) throws FileNotFoundException {
        //get stop word
        HashSet<String> stopWords = new HashSet<>(List.of(new Scanner(new File("stop_words.txt")).nextLine().split(",")));

        File file = new File("pride-and-prejudice.txt");
        Scanner scanner = new Scanner(file);
        List<String> allWords = new ArrayList<>();
        while (scanner.hasNext()) {
            // split string into line
            String line = scanner.nextLine();
            // recursively parsing the word char by char
            parsing(line, 0, stopWords, allWords);
        }

        HashSet<String> uniqueWords = new HashSet<>(allWords);
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : uniqueWords)  {
            map.put(word, Collections.frequency(allWords, word));
        }
        List<Map.Entry<String, Integer>> termFrequency = new ArrayList<>(map.entrySet());
        Collections.sort(termFrequency, (o1, o2) -> o2.getValue() - o1.getValue());
        for (int i = 0; i < 25; i++) {
            System.out.println(termFrequency.get(i).toString().replace("=", "  -  "));
        }
    }

    private static void parsing(String line, int startIndex, HashSet<String> stopWords, List<String> allWords) {
        if (startIndex >= line.length()) {
            return;
        }
        for (int i = startIndex; i < line.length(); i++) {
            if (checkValid(line.charAt(i))) {
                if (i == line.length() - 1) {
                    String word = line.substring(startIndex).toLowerCase();
                    if (!stopWords.contains(word)) {
                        allWords.add(word);
                    }
                }
                continue;
            } else {
                String word = line.substring(startIndex, i).toLowerCase();
                if (!stopWords.contains(word)) {
                    allWords.add(word);
                }
                parsing(line, i + 1, stopWords, allWords);
                break;
            }
        }
    }

    private static boolean checkValid(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}
