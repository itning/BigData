package top.itning.netty.http;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author itning
 * @since 2021/12/21 10:16
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class GlobalExceptionHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("捕获错误", cause);
        ctx.writeAndFlush(HttpResponseUtils.internalServerError());
    }
}
