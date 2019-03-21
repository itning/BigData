package top.itning.netty.chat.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.IOException;
import java.net.SocketAddress;

/**
 * 聊天服务器规则处理器
 *
 * @author itning
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private static final ChannelGroup CHANNELS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        final SocketAddress remoteAddress = ctx.channel().remoteAddress();
        ReferenceCountUtil.retain(msg);
        CHANNELS.stream()
                .filter(channel -> !remoteAddress.equals(channel.remoteAddress()))
                .forEach(channel -> channel.writeAndFlush(msg));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        final Channel channel = ctx.channel();
        CHANNELS.add(channel);
        System.out.println("上线: " + channel.remoteAddress());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("下线: " + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (!(cause instanceof IOException)) {
            cause.printStackTrace();
        }
    }
}
