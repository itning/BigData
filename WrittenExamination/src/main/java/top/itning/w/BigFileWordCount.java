package top.itning.w;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/**
 * 单词计数
 *
 * @author itning
 * @since 2021/12/2 20:40
 */
public class BigFileWordCount {
    public static void main(String[] args) throws IOException {
        BigFileWordCount bigFileWordCount = new BigFileWordCount();
        bigFileWordCount.doStart(200, "D:\\data.txt", "D:\\da.txt", "D:\\a\\");
    }

    private void doStart(int fileNum, String dataPath, String targetPath, String tempDir) throws IOException {
        if (!tempDir.endsWith(File.separator)) {
            tempDir += File.separator;
        }
        fileNum = normalizedCapacity(fileNum);
        // 1.拆分
        split(fileNum, dataPath, tempDir);
        // 2.单文件排序
        File[] files = singleFileSort(tempDir);
        // 3.合并
        merge(files, targetPath);
    }

    private void split(int fileNum, String dataPath, String tempDir) throws IOException {
        System.out.println("开始拆分初始化");

        BufferedWriter[] bufferedWriters = new BufferedWriter[fileNum];
        for (int i = 0; i < fileNum; i++) {
            File file = new File(tempDir + i + ".txt");
            if (file.exists()) {
                file.delete();
            }
            bufferedWriters[i] = new BufferedWriter(new FileWriter(tempDir + i + ".txt"));
        }
        System.out.println("开始拆分");
        readFile(dataPath, word -> {
            try {
                int i = hash(word) & fileNum - 1;
                BufferedWriter bufferedWriter = bufferedWriters[i];
                bufferedWriter.write(word + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        for (BufferedWriter bufferedWriter : bufferedWriters) {
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File[] singleFileSort(String tempDir) throws IOException {
        System.out.println("开始单文件排序");
        File[] files = new File(tempDir).listFiles();
        if (files == null) {
            return new File[0];
        }
        HashMap<String, Long> map = new HashMap<>(4096);
        for (File file : files) {
            System.out.println("文件[" + file + "]开始排序");
            long start = System.currentTimeMillis();
            readFile(file.getPath(), a -> {
                Long value = map.get(a);
                if (value == null) {
                    map.put(a, 1L);
                } else {
                    value++;
                    map.put(a, value);
                }
            });

            List<WordWrapper> list = new ArrayList<>(map.size());
            map.forEach((word, num) -> list.add(new WordWrapper(word, num)));
            list.sort((o1, o2) -> Long.compare(o2.count, o1.count));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            for (WordWrapper wordWrapper : list) {
                bufferedWriter.write(wordWrapper.toString());
            }
            int size = map.size();
            map.clear();
            bufferedWriter.flush();
            bufferedWriter.close();
            System.out.println("文件[" + file + "]排序完成，耗时：" + (System.currentTimeMillis() - start) + "ms size:" + size);
        }
        return files;
    }

    private void merge(File[] files, String targetPath) throws IOException {
        System.out.println("开始合并");
        BufferedReader[] bufferedReaders = new BufferedReader[files.length];
        for (int i = 0; i < files.length; i++) {
            bufferedReaders[i] = new BufferedReader(new FileReader(files[i]));
        }
        boolean[] done = new boolean[files.length];
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetPath, false));
        List<WordWrapper> list = new ArrayList<>(files.length);
        do {
            for (int i = 0; i < files.length; i++) {
                if (done[i]) {
                    continue;
                }
                BufferedReader bufferedReader = bufferedReaders[i];
                bufferedReader.mark(1024 * 1024);
                WordWrapper wordWrapper = readLine(bufferedReader);
                if (wordWrapper == null) {
                    done[i] = true;
                    continue;
                }
                wordWrapper.setBufferedReader(bufferedReader);
                list.add(wordWrapper);
            }
            if (list.isEmpty()) {
                continue;
            }
            list.sort((o1, o2) -> Long.compare(o2.count, o1.count));
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    bufferedWriter.write(list.get(0).toString());
                    continue;
                }
                list.get(i).getBufferedReader().reset();
            }
            list.clear();
        } while (!isAllDone(done));

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private int hash(String key) {
        int h;
        return (h = key.hashCode()) ^ (h >>> 16);
    }

    private int normalizedCapacity(int reqCapacity) {
        int normalizedCapacity = reqCapacity;
        // 这一行是针对已经是2的n次幂的reqCapacity，做一个适配，变成2的n次幂-1，走一样的逻辑
        normalizedCapacity--;
        // 把最高的一个1位涂抹到所有低位上（只关注最高的一个1位即可，抽象）
        // 这一步做完一定是00000111111这样的，第一个出现的1就是原始最高位的1
        normalizedCapacity |= normalizedCapacity >>> 1;
        normalizedCapacity |= normalizedCapacity >>> 2;
        normalizedCapacity |= normalizedCapacity >>> 4;
        normalizedCapacity |= normalizedCapacity >>> 8;
        normalizedCapacity |= normalizedCapacity >>> 16;
        // 最后加上1，保证是2的n次幂
        normalizedCapacity++;
        // 考虑溢出的情况（reqCapacity=Integer.MAX_VALUE），这里会是1开头+31个0
        if (normalizedCapacity < 0) {
            // 设置为01开头+30个0
            normalizedCapacity >>>= 1;
        }
        return normalizedCapacity;
    }

    private WordWrapper readLine(BufferedReader bufferedReader) throws IOException {
        String s = bufferedReader.readLine();
        if (null == s) {
            return null;
        }
        String[] s1 = s.split(" ");
        return new WordWrapper(s1[0], Long.parseLong(s1[1]));
    }

    private boolean isAllDone(boolean[] done) {
        for (boolean b : done) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    private void readFile(String path, Consumer<String> consumer) throws IOException {
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

    public static class WordWrapper implements Comparable<WordWrapper> {
        private final String word;
        private final long count;
        private BufferedReader bufferedReader;

        public WordWrapper(String word, long count) {
            this.word = word;
            this.count = count;
        }

        public BufferedReader getBufferedReader() {
            return bufferedReader;
        }

        public void setBufferedReader(BufferedReader bufferedReader) {
            this.bufferedReader = bufferedReader;
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