package top.itning.w;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author itning
 * @since 2021/12/3 10:42
 */
public class RandomWordGenerate {

    private final static int MIN_LETTERS = 3;
    private final static int MAX_LETTERS = 5;

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\data.txt"));

        for (int i = 0; i < 10000000; i++) {
            String word = createWord(MIN_LETTERS, MAX_LETTERS);
            bufferedWriter.write(word);
            if (Math.random() > 0.5) {
                bufferedWriter.write("\n");
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static String createWord(int min, int max) {
        int count = (int) (Math.random() * (max - min + 1)) + min;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < count; i++) {
            str.append((char) ((int) (Math.random() * 26) + 'a'));
        }
        return str.toString();
    }
}
