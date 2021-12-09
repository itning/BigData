package top.itning.netty.http;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author itning
 * @since 2021/12/9 11:05
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class BusinessHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final HttpRequestRouter httpRequestRouter;
    private final HttpRequestActuator httpRequestActuator;
    private final ExecutorService executorService;

    @Autowired
    public BusinessHandler(HttpRequestRouter httpRequestRouter, HttpRequestActuator httpRequestActuator) {
        super(false);
        this.httpRequestRouter = httpRequestRouter;
        this.httpRequestActuator = httpRequestActuator;
        executorService = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(), new DefaultThreadFactory("Business"));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        executorService.execute(() -> {
            try {
                HttpMethod method = msg.method();
                String uri = msg.uri();
                String contentTypeHeader = msg.headers().get(HttpHeaderNames.CONTENT_TYPE);

                Optional<Route> routeOptional = httpRequestRouter.router(method, uri, contentTypeHeader);

                if (!routeOptional.isPresent()) {
                    DefaultFullHttpResponse notFoundResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
                    notFoundResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
                    ctx.writeAndFlush(notFoundResponse);
                    return;
                }

                Route route = routeOptional.get();
                if (!route.isSupportMethod(method)) {
                    DefaultFullHttpResponse methodNotAllowedResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.METHOD_NOT_ALLOWED);
                    methodNotAllowedResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
                    ctx.writeAndFlush(methodNotAllowedResponse);
                    return;
                }

                HttpResponse response = httpRequestActuator.execute(route, msg.content());
                ctx.writeAndFlush(response);

            } catch (Throwable e) {
                log.error("处理请求异常", e);
                ctx.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR));
                ctx.channel().close();
            } finally {
                ReferenceCountUtil.release(msg);
            }
        });
    }
}
