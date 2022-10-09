package cat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;
import lombok.extern.java.Log;
import servlet.CatServlet;

/**
 * @Author forest
 * @Date 2022/10/7 22:02
 * @Version 1.0
 */
@Log
public class CatServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(ctx.toString());
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            CatHttpRequest heroRequest = new CatHttpRequest(request);
            log.info(heroRequest.getParameters().toString());
            CatServlet servlet = new DefaultCatServlet();


        }

    }
}
