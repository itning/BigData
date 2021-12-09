package top.itning.netty.http;

import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author itning
 * @since 2021/12/9 14:16
 */
@Slf4j
@Component
public class HttpRequestRouter {

    private static final Map<String, Route> ROUTE_MAP = new HashMap<>();

    public HttpRequestRouter() {
        Route route = new Route("/users", HttpMethod.POST);
        ROUTE_MAP.put(route.getUri(), route);
    }

    public Optional<Route> router(HttpMethod method, String uri, String contentType) {
        log.info("请求方法：{} 请求路径：{} 内容类型：{}", method, uri, contentType);
        String path = uri;
        int queryStartIndex = uri.indexOf('?');
        if (queryStartIndex != -1) {
            path = uri.substring(0, queryStartIndex);
        }
        Route route = ROUTE_MAP.get(path);
        if (null != route) {
            route.setPath(path);
            route.setUri(uri);
            if (queryStartIndex != -1) {
                try {
                    route.setQueryParams(splitQuery(uri.substring(queryStartIndex + 1)));
                } catch (Exception e) {
                    log.error("解析查询参数失败", e);
                    return Optional.empty();
                }
            }
        }
        return Optional.ofNullable(route);
    }

    public static Map<String, List<String>> splitQuery(String uri) throws UnsupportedEncodingException {
        final Map<String, List<String>> queryPairs = new LinkedHashMap<>();
        final String[] pairs = uri.split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!queryPairs.containsKey(key)) {
                queryPairs.put(key, new LinkedList<>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            queryPairs.get(key).add(value);
        }
        return queryPairs;
    }
}
