package top.itning.bigdata.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

/**
 * jdk7主要增加了三个新的异步通道:
 * AsynchronousFileChannel: 用于文件异步读写；
 * AsynchronousSocketChannel: 客户端异步socket；
 * AsynchronousServerSocketChannel: 服务器异步socket。
 *
 * @author itning
 */
@SuppressWarnings("Duplicates")
public class TestAsynchronousFileChannel {
    public static void main(String[] a) throws IOException, InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Path path = Paths.get("C:\\Users\\wangn\\Desktop", "一团头发缠大米饭里.html");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println(result);
                System.out.println(attachment.toString());
                System.out.println(new String(attachment.array(), StandardCharsets.UTF_8));
                countDownLatch.countDown();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println(exc.getMessage());
                System.out.println(attachment.toString());
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }
}
