package top.itning.netty.simple.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 简单服务端规则处理器
 *
 * @author wangn
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            System.out.println(msg.getClass().getName());
            ByteBuf in = (ByteBuf) msg;
            // 打印客户端输入，传输过来的的字符
            System.out.println(in.toString(CharsetUtil.UTF_8));
            ByteBuf buffer = ctx
                    .alloc()
                    .buffer()
                    .writeBytes("哈哈".getBytes());
            ChannelFuture channelFuture = ctx.writeAndFlush(buffer);
            // 你可以简单的使用
            channelFuture.addListener(ChannelFutureListener.CLOSE);
            // 或者自己监听
            /*channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    ctx.close();
                }
            });*/
        } finally {
            // ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放。
            // 请记住处理器的职责是释放所有传递到处理器的引用计数对象。
            // 抛弃收到的数据
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
