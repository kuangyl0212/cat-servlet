package com.forest.cat;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import com.forest.servlet.CatRequest;

import java.util.List;
import java.util.Map;

/**
 * @Author forest
 * @Date 2022/9/24 23:08
 * @Version 1.0
 */
public class CatHttpRequest implements CatRequest {
    private final HttpRequest request;

    public CatHttpRequest(HttpRequest request) {
        this.request = request;
    }

    @Override
    public String getUri() {
        return request.uri();
    }

    @Override
    public String getPath() {
        return new QueryStringDecoder(request.uri()).path();
    }

    @Override
    public String getMethod() {
        return request.method().name();
    }

    @Override
    public Map<String, List<String>> getParameters() {
        return new QueryStringDecoder(request.uri()).parameters();
    }

    @Override
    public List<String> getParameters(String name) {
        return getParameters().get(name);
    }

    @Override
    public String getParameter(String name) {
        List<String> res = getParameters().get(name);
        if (res == null || res.size() == 0) {
            return null;
        }
        return res.get(0);
    }
}
