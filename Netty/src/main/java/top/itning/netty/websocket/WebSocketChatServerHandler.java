package top.itning.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author itning
 * @date 2020/4/6 14:12
 */
public class WebSocketChatServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final ChannelGroup CHANNELS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = System.currentTimeMillis() + ": " + msg.text();
        CHANNELS.forEach(channel -> channel.writeAndFlush(new TextWebSocketFrame(text)));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        final Channel channel = ctx.channel();
        System.out.println("up: " + channel.remoteAddress());
        CHANNELS.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        final Channel channel = ctx.channel();
        System.out.println("down: " + channel.remoteAddress());
        CHANNELS.remove(channel);
    }
}
