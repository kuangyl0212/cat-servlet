package com.forest.cat;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import com.forest.servlet.CatResponse;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Author forest
 * @Date 2022/9/24 23:09
 * @Version 1.0
 */
public class CatHttpResponse implements CatResponse {

    private final ChannelHandlerContext context;
    private FullHttpResponse response;

    private final HttpRequest request;

    public CatHttpResponse(ChannelHandlerContext context, FullHttpResponse response, HttpRequest request) {
        this.context = context;
        this.response = response;
        this.request = request;
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

    @Override
    public void write(File file) throws Exception {

        String path = file.getPath();
        // 设置文件格式内容
        if (path.endsWith(".html")){
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8");
        }else if(path.endsWith(".js")){
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "application/x-javascript");
        }else if(path.endsWith(".css")){
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/css; charset=UTF-8");
        } else if (isImage(path)) {
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "image/" + getFileExtension(path));
        }

        FileChannel channel = new FileInputStream(file).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            response.content().writeBytes(buffer);
            buffer.clear();
        }
        channel.close();

        long fileLength = response.content().readableBytes();
        HttpUtil.setContentLength(response, fileLength);
        context.writeAndFlush(response);

    }

    private static String getFileExtension(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }

    private static boolean isImage(String path) {
        return path.endsWith(".jpg") || path.endsWith(".png") || path.endsWith(".jpeg");
    }
}
