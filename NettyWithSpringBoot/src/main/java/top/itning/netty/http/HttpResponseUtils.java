package top.itning.netty.http;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.StandardCharsets;

/**
 * @author itning
 * @since 2021/12/21 9:58
 */
public class HttpResponseUtils {

    public static DefaultFullHttpResponse badRequestResponse() {
        DefaultFullHttpResponse notFoundResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
        notFoundResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
        return notFoundResponse;
    }

    public static DefaultFullHttpResponse notFoundResponse() {
        DefaultFullHttpResponse notFoundResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
        notFoundResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
        return notFoundResponse;
    }

    public static DefaultFullHttpResponse internalServerError() {
        return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }

    public static DefaultFullHttpResponse methodNotAllowedResponse() {
        DefaultFullHttpResponse methodNotAllowedResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.METHOD_NOT_ALLOWED);
        methodNotAllowedResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
        return methodNotAllowedResponse;
    }

    public static DefaultFullHttpResponse toJsonOkResponse(Object object) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(JSON.toJSONString(object).getBytes(StandardCharsets.UTF_8));
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        defaultFullHttpResponse.headers()
                .set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=utf-8")
                .set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        return defaultFullHttpResponse;
    }

    public static DefaultFullHttpResponse toHtmlOkResponse(String content) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8));
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        defaultFullHttpResponse.headers()
                .set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=utf-8")
                .set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        return defaultFullHttpResponse;
    }
}
