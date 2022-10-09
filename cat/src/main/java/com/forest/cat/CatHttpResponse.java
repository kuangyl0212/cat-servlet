package com.forest.cat;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import com.forest.servlet.CatResponse;

import java.nio.charset.StandardCharsets;

/**
 * @Author forest
 * @Date 2022/9/24 23:09
 * @Version 1.0
 */
public class CatHttpResponse implements CatResponse {

    private final ChannelHandlerContext context;
    private FullHttpResponse response;

    public CatHttpResponse(ChannelHandlerContext context, FullHttpResponse response) {
        this.context = context;
        this.response = response;
    }

    @Override
    public void write(String content) throws Exception {
        this.response = this.response.replace(Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8)));
        this.response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes()); // 需要注明响应体的长度，否则客户端不知道什么时候停下来
        this.context.writeAndFlush(response);
    }

    @Override
    public void setStatus(HttpResponseStatus status) {
        this.response.setStatus(status);
    }
}
