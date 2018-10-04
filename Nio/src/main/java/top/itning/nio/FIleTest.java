package top.itning.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * 文件读写
 *
 * @author wangn
 */
public class FIleTest {
    public static void main(String[] args) {
        String inFile = Objects.requireNonNull(FIleTest.class.getClassLoader().getResource("test.txt")).getFile();
        try (FileInputStream fin = new FileInputStream(inFile);
             FileChannel fc = fin.getChannel();
             FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\wangn\\Desktop\\out.txt");
             FileChannel outputStreamChannel = fileOutputStream.getChannel()) {
            // ByteBuffer buffer = ByteBuffer.allocate(1024);
            // fc.read(buffer);
            // System.out.println(new String(buffer.array()));
            System.out.println(fc.size());
            outputStreamChannel.transferFrom(fc, 0, fc.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
