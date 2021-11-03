package top.itning.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author itning
 * @since 2021/11/3 13:53
 */
public class EchoReactorClient {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try (SocketChannel socketChannel = SocketChannel.open()) {
            //是否阻塞
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8657));
            if (socketChannel.finishConnect()) {
                Selector selector = Selector.open();
                socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                while (selector.select() > 0) {
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> it = selected.iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        if (key.isWritable()) {
                            System.out.println("可写");
                            Scanner scanner = new Scanner(System.in);
                            if (scanner.hasNext()) {
                                String s = scanner.next();
                                socketChannel.write(ByteBuffer.wrap(s.getBytes()));
                            }
                        }

                        if (key.isReadable()) {
                            System.out.println("可读");
                            buffer.clear();
                            socketChannel.read(buffer);
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, buffer.limit()));
                        }
                        it.remove();
                    }
                }
                socketChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
