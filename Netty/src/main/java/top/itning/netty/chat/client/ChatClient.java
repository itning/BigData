package top.itning.netty.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.itning.netty.chat.codec.ChatDecoder;
import top.itning.netty.chat.codec.ChatEncoder;
import top.itning.netty.chat.entity.ChatMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 聊天服务器客户端
 *
 * @author itning
 */
public class ChatClient {

    private static final String EXIT_MSG = "esc";

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Channel channel = new Bootstrap()
                    .group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChatEncoder(), new ChatDecoder(), new ChatClientHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .connect("127.0.0.1", 8866)
                    .sync()
                    .channel();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.print("输入昵称，回车继续：");
                String nickName = in.readLine();
                while (true) {
                    String s = in.readLine();
                    if (s == null || s.equals(EXIT_MSG)) {
                        break;
                    }
                    channel.writeAndFlush(new ChatMessage(nickName, s));
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
