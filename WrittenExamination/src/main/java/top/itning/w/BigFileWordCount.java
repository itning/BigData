package top.itning.w;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.function.Consumer;

/**
 * 单词计数
 * 拆分：先把文件按行读取，读取一行后，进行取模操作，放到不同文件中，这样同样的单词在同一个文件中
 * 排序：对每个文件单词出现的次数进行排序，这里需要对某个拆分文件按行读取然后放到MAP中，最后在写入文件中
 * 合并：先读取每个文件的第一行，比较大小，最大的写入汇总文件中，其他的读取指针回退，重复该操作直到所有文件都读取完成。
 *
 * @author itning
 * @since 2021/12/2 20:40
 */
public class BigFileWordCount {
    public static void main(String[] args) throws IOException {
        BigFileWordCount bigFileWordCount = new BigFileWordCount();
        bigFileWordCount.doStart(10, "D:\\data1.txt", "D:\\da.txt", "D:\\a\\");
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

    /**
     * 每次每个文件读一行，取最高的，其余的放回（指针回退）,循环
     */
    private void merge(File[] files, String targetPath) throws IOException {
        System.out.println("开始合并");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetPath, false));
        List<WordWrapper> list = new ArrayList<>(files.length);
        LinkedList<BufferedReader> scanFileList = new LinkedList<>();
        for (File file : files) {
            scanFileList.add(new BufferedReader(new FileReader(file)));
        }
        do {
            Iterator<BufferedReader> iterator = scanFileList.listIterator(0);
            while (iterator.hasNext()) {
                BufferedReader bufferedReader = iterator.next();
                bufferedReader.mark(1024 * 1024);
                WordWrapper wordWrapper = readLine(bufferedReader);
                if (wordWrapper == null) {
                    iterator.remove();
                    System.out.println("一个文件读取完成 " + System.currentTimeMillis());
                    continue;
                }
                wordWrapper.setBufferedReader(bufferedReader);
                list.add(wordWrapper);
            }

            if (list.isEmpty()) {
                continue;
            }
            list.sort((o1, o2) -> Long.compare(o2.count, o1.count));
            WordWrapper wordWrapper = list.get(0);
            bufferedWriter.write(wordWrapper.toString());
            for (int i = 1; i < list.size(); i++) {
                if (wordWrapper.count == list.get(i).count) {
                    bufferedWriter.write(list.get(i).toString());
                    continue;
                }
                list.get(i).getBufferedReader().reset();
            }
            list.clear();
        } while (!scanFileList.isEmpty());

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

    /**
     * 将文件分割成单个小文件
     */
    private void split(String filePath, String targetDir, int wantSplitNum) throws IOException {
        List<SegmentedFile> split = split(filePath, wantSplitNum);
        System.out.println(split);
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
        FileChannel channel = randomAccessFile.getChannel();

        for (SegmentedFile segmentedFile : split) {
            FileOutputStream fileOutputStream = new FileOutputStream(targetDir + segmentedFile.toString() + ".txt");
            FileChannel writeableChannel = fileOutputStream.getChannel();
            channel.transferTo(segmentedFile.start, segmentedFile.getLength(), writeableChannel);
            writeableChannel.close();
            fileOutputStream.close();
        }
    }

    private List<SegmentedFile> split(String filePath, int wantSplitNum) throws IOException {
        List<SegmentedFile> segmentedFiles = new ArrayList<>(wantSplitNum);
        File file = new File(filePath);
        long length = file.length();
        System.out.println("文件总长度 " + length);
        long singleFileLength = length / wantSplitNum;
        System.out.println("期望每个文件长度 " + singleFileLength + " " + singleFileLength + "*" + wantSplitNum + "+" + (length - (singleFileLength * wantSplitNum)) + "=" + length);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long startPos = 0L;
        for (int i = 0; i < wantSplitNum; i++) {
            if (i == wantSplitNum - 1) {
                long end = startPos + (length - startPos);
                System.out.println("文件 " + i + " start " + startPos + " end " + end + " length " + (end - startPos));
                segmentedFiles.add(new SegmentedFile(startPos, end));
                break;
            }
            Long pos;
            if (i == 0) {
                pos = getTheNextLineBreakPosition(randomAccessFile, singleFileLength);
            } else {
                pos = getTheNextLineBreakPosition(randomAccessFile, singleFileLength + startPos);
            }
            System.out.println("文件 " + i + " start " + startPos + " end " + pos + " length " + (pos - startPos));
            segmentedFiles.add(new SegmentedFile(startPos, pos));
            startPos = pos;
        }
        return segmentedFiles;
    }

    private Long getTheNextLineBreakPosition(RandomAccessFile randomAccessFile, long startSearchPos) throws IOException {
        randomAccessFile.seek(startSearchPos);
        System.out.println("当前文件位置 " + randomAccessFile.getFilePointer());
        int read;
        // 没有找到换行符返回null
        Long pos = null;
        while ((read = randomAccessFile.read()) != -1) {
            if (read == '\n') {
                pos = randomAccessFile.getFilePointer();
                break;
            }
            if (read == '\r') {
                long temp = randomAccessFile.getFilePointer();
                if (randomAccessFile.read() != '\n') {
                    randomAccessFile.seek(temp);
                }
                pos = randomAccessFile.getFilePointer();
                break;
            }
        }
        System.out.println("查找到的位置 " + pos);
        return pos;
    }

    public static class SegmentedFile {
        private final long start;
        private final long end;

        public SegmentedFile(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getLength() {
            return end - start;
        }

        @Override
        public String toString() {
            return "{" + start + "-" + end + "}";
        }
    }
}