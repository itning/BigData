package top.itning.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author itning
 * @since 2021/11/3 13:06
 */
public class EchoReactorSingleServer implements Runnable {
    @Override
    public void run() {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.socket().bind(new InetSocketAddress(8657));
            serverSocketChannel.configureBlocking(false);
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new AcceptHandler(serverSocketChannel, selector));
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    Runnable runnable = (Runnable) key.attachment();
                    if (null != runnable) {
                        runnable.run();
                    }
                    iterator.remove();
                }
            }
            System.out.println(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class AcceptHandler implements Runnable {
        private final ServerSocketChannel serverSocketChannel;
        private final Selector selector;

        public AcceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) {
            this.serverSocketChannel = serverSocketChannel;
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                SocketChannel accept = serverSocketChannel.accept();
                if (null != accept) {
                    System.out.println("收到连接事件 " + accept.getRemoteAddress());
                    new EchoHandler(accept, selector);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class EchoHandler implements Runnable {
        private final ByteBuffer BYTE_BUFFER = ByteBuffer.allocate(1024);

        private final SocketChannel socketChannel;
        private SelectionKey selectionKey;

        private boolean sendState = false;

        public EchoHandler(SocketChannel socketChannel, Selector selector) {
            this.socketChannel = socketChannel;
            try {
                socketChannel.configureBlocking(false);
                selectionKey = socketChannel.register(selector, 0);
                selectionKey.attach(this);
                selectionKey.interestOps(SelectionKey.OP_READ);
                // selector.wakeup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                if (sendState) {
                    System.out.println("写事件 " + socketChannel.getRemoteAddress());
                    socketChannel.write(BYTE_BUFFER);
                    BYTE_BUFFER.clear();
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    sendState = false;
                } else {
                    System.out.println("读事件 " + socketChannel.getRemoteAddress());
                    int length;
                    while ((length = socketChannel.read(BYTE_BUFFER)) > 0) {
                        System.out.println(new String(BYTE_BUFFER.array(), 0, length));
                    }
                    BYTE_BUFFER.flip();
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                    sendState = true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
