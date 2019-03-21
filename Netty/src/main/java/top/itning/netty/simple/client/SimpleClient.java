package top.itning.netty.simple.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 简单客户端
 *
 * @author itning
 */
public class SimpleClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            new Bootstrap()
                    .group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new SimpleClientHandler());
                        }
                    })
                    .connect("127.0.0.1", 8080)
                    .sync()
                    // 等待连接关闭
                    .channel()
                    .closeFuture()
                    .sync();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
