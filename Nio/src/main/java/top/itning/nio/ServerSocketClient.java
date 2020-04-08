package top.itning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @author wangn
 */
public class ServerSocketClient {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //是否阻塞
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8080));
            if (socketChannel.finishConnect()) {
                int i = 0;
                //循环发送
                while (i < 5) {
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm " + i++ + "-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    //翻转 使可读
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }
                socketChannel.close();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
