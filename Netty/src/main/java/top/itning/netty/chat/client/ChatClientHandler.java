package top.itning.netty.chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.itning.netty.chat.entity.ChatMessage;

/**
 * 聊天服务器客户端规则处理器
 *
 * @author itning
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<ChatMessage> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChatClientHandler::channelActive");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMessage msg) throws Exception {
        System.out.println(msg);
    }
}
