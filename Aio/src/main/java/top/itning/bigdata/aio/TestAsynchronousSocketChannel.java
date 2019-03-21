package top.itning.bigdata.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * jdk7主要增加了三个新的异步通道:
 * AsynchronousFileChannel: 用于文件异步读写；
 * AsynchronousSocketChannel: 客户端异步socket；
 * AsynchronousServerSocketChannel: 服务器异步socket。
 *
 * @author itning
 */
public class TestAsynchronousSocketChannel {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 8080)).get();
        ByteBuffer b1 = ByteBuffer.allocate(1024);
        channel.read(b1, b1, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println(result);
                System.out.println(new String(attachment.array()));
                countDownLatch.countDown();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
        ByteBuffer buffer = ByteBuffer.wrap("中文,你好".getBytes());
        Future future = channel.write(buffer);
        future.get();
        System.out.println("send ok");
        countDownLatch.await();
    }
}
