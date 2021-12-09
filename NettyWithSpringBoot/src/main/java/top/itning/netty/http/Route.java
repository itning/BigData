package top.itning.netty.http;

import io.netty.handler.codec.http.HttpMethod;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author itning
 * @since 2021/12/9 14:21
 */
@Data
public class Route {

    public Route(String uri, HttpMethod method) {
        this.uri = uri;
        this.method = method;
    }

    private String path;
    private String uri;
    private Map<String, List<String>> queryParams = Collections.emptyMap();
    private HttpMethod method;

    public boolean isSupportMethod(HttpMethod method) {
        return this.method.equals(method);
    }
}
