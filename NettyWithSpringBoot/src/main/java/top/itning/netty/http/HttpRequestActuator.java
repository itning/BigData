package top.itning.netty.http;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.itning.netty.model.User;
import top.itning.netty.service.UserInfoService;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author itning
 * @since 2021/12/9 14:20
 */
@Slf4j
@Component
public class HttpRequestActuator {
    private final UserInfoService userInfoService;

    @Autowired
    public HttpRequestActuator(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public HttpResponse execute(Route route, ByteBuf bodyByteBuf) {
        String s = bodyByteBuf.toString(StandardCharsets.UTF_8);
        log.info("Route：{} Msg：{}", route, s);


        User user = JSON.parseObject(s, User.class);

        List<User> userList = userInfoService.getAll();

        return toJsonOkResponse(userList);
    }

    private DefaultFullHttpResponse toJsonOkResponse(Object object) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(JSON.toJSONString(object).getBytes(StandardCharsets.UTF_8));
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        defaultFullHttpResponse.headers()
                .set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=utf-8")
                .set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        return defaultFullHttpResponse;
    }
}
