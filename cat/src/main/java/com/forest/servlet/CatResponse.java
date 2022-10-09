package com.forest.servlet;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @Author forest
 * @Date 2022/9/24 23:06
 * @Version 1.0
 */
public interface CatResponse {
    // 将响应写入到Channel
    void write(String content) throws Exception;

    void setStatus(HttpResponseStatus status);
}
