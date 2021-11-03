package top.itning.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author itning
 * @since 2021/11/3 13:53
 */
public class EchoReactorClient {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //是否阻塞
            socketChannel.configureBlocking(true);
            socketChannel.connect(new InetSocketAddress("localhost", 8657));
            if (socketChannel.finishConnect()) {
                while (true) {
                    String s = scanner.nextLine();
                    if (s.equals("exit")) {
                        break;
                    }
                    socketChannel.write(ByteBuffer.wrap(s.getBytes()));
                    socketChannel.read(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, buffer.limit()));
                }
                socketChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
