package cat;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import servlet.CatResponse;

import java.nio.charset.StandardCharsets;

/**
 * @Author forest
 * @Date 2022/9/24 23:09
 * @Version 1.0
 */
public class CatHttpResponse implements CatResponse {

    private final ChannelHandlerContext context;

    public CatHttpResponse(ChannelHandlerContext context) {
        this.context = context;
    }

    @Override
    public void write(String content) throws Exception {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8)));
        HttpHeaders headers = response.headers();
        headers.set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes()); // 需要注明响应体的长度，否则客户端不知道什么时候停下来
        this.context.writeAndFlush(response);
    }
}
