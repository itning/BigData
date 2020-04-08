package top.itning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author wangn
 */
public class ServerSocketServer {
    private static final int BUF_SIZE = 1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;

    public static void main(String[] args) {
        selector();
    }

    private static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        long bytesRead = sc.read(buf);
        while (bytesRead > 0) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if (bytesRead == -1) {
            sc.close();
        }
    }

    private static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }

    private static void selector() {
        try (Selector selector = Selector.open();
             ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.socket().bind(new InetSocketAddress(PORT));
            ssc.configureBlocking(false);
            //第二个参数:通过Selector监听Channel时对什么事件感兴趣
            //连接、接受、读或写
            //1. SelectionKey.OP_CONNECT
            //2. SelectionKey.OP_ACCEPT
            //3. SelectionKey.OP_READ
            //4. SelectionKey.OP_WRITE
            SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("readyOps:" + selectionKey.readyOps());
            while (true) {
                //最长会阻塞timeout毫秒
                //select()方法返回的int值表示有多少通道已经就绪
                if (selector.select(TIMEOUT) == 0) {
                    System.out.println("==");
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isValid() && key.isWritable()) {
                        handleWrite(key);
                    }
                    if (key.isValid() && key.isConnectable()) {
                        System.out.println("is Connect able = true");
                    }
                    iter.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
