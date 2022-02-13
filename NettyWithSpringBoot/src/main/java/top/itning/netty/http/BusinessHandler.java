package top.itning.netty.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.itning.netty.model.User;

import java.nio.charset.StandardCharsets;
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

    private final ExecutorService executorService;

    @Autowired
    public BusinessHandler() {
        super(false);
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
                QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
                log.info("路径[{}]方法[{}]类型[{}]查询参数[{}]", method, uri, contentTypeHeader, queryStringDecoder.parameters());

                if (HttpMethod.GET.equals(method)) {
                    if (uri.startsWith("/user")) {
                        ctx.writeAndFlush(HttpResponseUtils.toJsonOkResponse(new User(1L, "张三")));
                        return;
                    }
                    ctx.writeAndFlush(HttpResponseUtils.notFoundResponse());
                    return;
                }

                if (HttpMethod.POST.equals(method)) {
                    if (uri.startsWith("/user") && null != contentTypeHeader) {
                        if (contentTypeHeader.trim().startsWith("application/json")) {
                            String json = msg.content().toString(StandardCharsets.UTF_8);
                            try {
                                JSONObject jsonObject = JSON.parseObject(json);
                                log.info("POST请求体[{}]", jsonObject);
                                ctx.writeAndFlush(HttpResponseUtils.toHtmlOkResponse("<h1>OK</h1>"));
                            } catch (Exception e) {
                                log.warn("解析JSON异常[{}]", json, e);
                                ctx.writeAndFlush(HttpResponseUtils.badRequestResponse());
                            }
                            return;
                        } else {
                            ctx.writeAndFlush(HttpResponseUtils.methodNotAllowedResponse());
                        }
                    }
                    ctx.writeAndFlush(HttpResponseUtils.notFoundResponse());
                    return;
                }
                ctx.writeAndFlush(HttpResponseUtils.methodNotAllowedResponse());
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
