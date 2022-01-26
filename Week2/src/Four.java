import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Four {
    static class TF {
        public String word;
        public int count;

        public TF(String word) {
            this.word = word;
            this.count = 1;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // load stop words
        File stopFile = new File("stop_words.txt");
        Scanner stopScanner = new Scanner(stopFile);
        String[] stopWordsArray = stopScanner.nextLine().split(",");

        ArrayList<TF> wordFrequency = new ArrayList<>();

        // load data file
        File dataFile = new File("pride-and-prejudice.txt");
        Scanner dataScanner = new Scanner(dataFile);
        while (dataScanner.hasNextLine()) {
            String line = dataScanner.nextLine();
            // if newWord is -1 means that we try to find a new word
            // if is not means that we are finding a word and this is the start index.
            int newWord = -1;
            int endIndex = 0;
            for (int i = 0; i < line.length(); i++) {
                if (newWord == -1) {
                    // start index of new word
                    if (checkValid(line.charAt(i))) {
                        newWord = i;
                    }
                } else {
                    String currentWord = "";
                    boolean wordEnd = false;
                    //we find the end index of the word
                    if (!checkValid(line.charAt(i))) {
                        if (endIndex - newWord >= 1) {
                            currentWord = line.substring(newWord, endIndex).toLowerCase();
                            wordEnd = true;
                        }
                    } else {
                        // if reach the last char in line that we also think this is a new word
                        if (i == line.length() - 1) {
                            currentWord = line.substring(newWord).toLowerCase();
                            wordEnd = true;
                        }
                    }

                    // if we found a word
                    if (wordEnd) {
                        // check if it is a stop word
                        boolean isStop = false;
                        for (int j = 0; j < stopWordsArray.length; j++) {
                            if (stopWordsArray[j].equals(currentWord)) {
                                isStop = true;
                                break;
                            }
                        }

                        // check if it is in the list
                        if (!isStop) {
                            boolean isShow = false;
                            for (TF tf : wordFrequency) {
                                if (tf.word.equals(currentWord)) {
                                    tf.count++;
                                    isShow = true;
                                    break;
                                }
                            }
                            // if not show in the word frequency then add
                            if (!isShow) {
                                wordFrequency.add(new TF(currentWord));
                            }

                            // after add count or add new word, we need to sort
                            if (wordFrequency.size() > 1) {
                                wordFrequency.sort((TF t1, TF t2) -> t2.count - t1.count);
                                }
                            }
                    // after we deal with the word we find, we reset the newWord to -1
                        newWord = -1;
                        }
                }
                endIndex++;
            }
        }

        int count = 0;
        for (TF tf : wordFrequency) {
            count++;
            System.out.println(tf.word + "  -  " + tf.count);
            if (count >= 25) {
                break;
            }
        }
    }
    private static boolean checkValid(char c) {
        boolean res = false;
        if (c >= 'a' && c <= 'z') {
            res = true;
        }
        if (c >= 'A' && c <= 'Z') {
            res = true;
        }
        if (c >= '0' && c <= '9') {
            res = true;
        }
        return res;
    }



}
