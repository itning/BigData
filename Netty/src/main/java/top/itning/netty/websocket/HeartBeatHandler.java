package top.itning.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author itning
 * @date 2020/4/7 12:55
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE: {
                    System.out.println("读空闲事件触发");
                    break;
                }
                case WRITER_IDLE: {
                    System.out.println("写空闲事件触发");
                    break;
                }
                case ALL_IDLE: {
                    // 在这里关闭连接
                    //ctx.channel().close();
                    System.out.println("读写空闲事件触发");
                    break;
                }
                default:
                    super.userEventTriggered(ctx, evt);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
