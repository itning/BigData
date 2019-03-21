package top.itning.bigdata.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
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
public class TestAsynchronousServerSocketChannel {
    public static void main(String[] args) throws IOException, InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel
                .open()
                .bind(new InetSocketAddress(8080));
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                serverSocketChannel.accept(null, this);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                result.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer integer, ByteBuffer attachment) {
                        System.out.println(integer);
                        System.out.println(attachment.toString());
                        System.out.println(new String(attachment.array(), StandardCharsets.UTF_8));
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.put("哈哈".getBytes());
                        buffer.flip();
                        result.write(buffer);
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        System.out.println(exc.getMessage());
                        System.out.println(attachment.toString());
                        countDownLatch.countDown();
                    }
                });

            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println(exc.getMessage());
                System.out.println(attachment.toString());
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }
}
