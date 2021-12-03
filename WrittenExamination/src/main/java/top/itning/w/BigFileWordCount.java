package top.itning.w;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

/**
 * 单词计数
 *
 * @author itning
 * @since 2021/12/2 20:40
 */
public class BigFileWordCount {
    public static void main(String[] args) throws IOException {
        doStart();
    }

    private static void doStart() throws IOException {
        int fileNum = 5;
        System.out.println("开始拆分");
        Map<Integer, BufferedWriter> bufferedWriterMap = new HashMap<>();
        for (int i = 0; i < fileNum; i++) {
            File file = new File("D:\\a\\" + i + ".txt");
            if (file.exists()) {
                file.delete();
            }
            bufferedWriterMap.put(i, new BufferedWriter(new FileWriter("D:\\a\\" + i + ".txt")));
        }
        // 1.拆分
        readFile("D:\\data.txt", word -> {
            try {
                int i = Math.abs(word.hashCode()) % fileNum;
                BufferedWriter bufferedWriter = bufferedWriterMap.get(i);
                bufferedWriter.write(word + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriterMap.forEach((i, b) -> {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("开始单文件排序");
        // 2.单文件排序
        File[] files = new File("D:\\a\\").listFiles();
        for (File file : files) {
            Map<String, Long> map = new HashMap<>();

            readFile(file.getPath(), a -> {
                Long value = map.get(a);
                if (value == null) {
                    map.put(a, 1L);
                } else {
                    value++;
                    map.put(a, value);
                }
            });


            List<WordWrapper> list = new ArrayList<>();
            map.forEach((word, num) -> list.add(new WordWrapper(word, num)));
            list.sort((o1, o2) -> Long.compare(o2.count, o1.count));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            for (WordWrapper wordWrapper : list) {
                bufferedWriter.write(wordWrapper.toString());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        System.out.println("开始合并");
        Map<Integer, BufferedReader> bufferedReaderMap = new HashMap<>();
        for (int i = 0; i < files.length; i++) {
            bufferedReaderMap.put(i, new BufferedReader(new FileReader(files[i])));
        }
        // 3.合并
        boolean[] done = new boolean[files.length];
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\da.txt", false));
        do {
            List<WordWrapper> cacheList = new ArrayList<>();
            List<WordWrapper> list = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                if (!cacheList.isEmpty()) {
                    list.addAll(cacheList);
                    cacheList.clear();
                }
                if (done[i]) {
                    continue;
                }
                BufferedReader bufferedReader = bufferedReaderMap.get(i);

                WordWrapper wordWrapper = readLine(bufferedReader);
                if (wordWrapper == null) {
                    done[i] = true;
                    continue;
                }
                list.add(wordWrapper);
                WordWrapper nextWordWrapper;
                do {
                    nextWordWrapper = readLine(bufferedReader);
                    if (nextWordWrapper == null) {
                        continue;
                    }
                    if (nextWordWrapper.count == wordWrapper.count) {
                        list.add(nextWordWrapper);
                    } else {
                        cacheList.add(nextWordWrapper);
                        break;
                    }
                } while (nextWordWrapper != null);
            }
            System.out.println("待排序集合大小 " + list.size());
            list.sort((o1, o2) -> Long.compare(o2.count, o1.count));
            for (WordWrapper wordWrapper : list) {
                bufferedWriter.write(wordWrapper.toString());
            }
        } while (!isAllDone(done));
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static WordWrapper readLine(BufferedReader bufferedReader) throws IOException {
        String s = bufferedReader.readLine();
        if (null == s) {
            return null;
        }
        String[] s1 = s.split(" ");
        return new WordWrapper(s1[0], Long.parseLong(s1[1]));
    }

    private static boolean isAllDone(boolean[] done) {
        for (boolean b : done) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    private static void readFile(String path, Consumer<String> consumer) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split("[^a-zA-Z]");
            for (String s : split) {
                if ("".equals(s.trim())) {
                    continue;
                }
                String word = s.toLowerCase(Locale.ROOT);
                consumer.accept(word);
            }
        }
    }

    public static void removeFirstLine(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        long writePosition = raf.getFilePointer();
        raf.readLine();
        long readPosition = raf.getFilePointer();

        byte[] buff = new byte[2048];
        int n;
        while (-1 != (n = raf.read(buff))) {
            raf.seek(writePosition);
            raf.write(buff, 0, n);
            readPosition += n;
            writePosition += n;
            raf.seek(readPosition);
        }
        raf.setLength(writePosition);
        raf.close();
    }

    public static class WordWrapper implements Comparable<WordWrapper> {
        private final String word;
        private final long count;

        public WordWrapper(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(WordWrapper o) {
            return Long.compare(o.count, count);
        }

        @Override
        public String toString() {
            return word + " " + count + "\n";
        }
    }
}