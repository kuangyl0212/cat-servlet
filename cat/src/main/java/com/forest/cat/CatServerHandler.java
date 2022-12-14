package com.forest.cat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import lombok.extern.java.Log;
import com.forest.servlet.CatRequest;
import com.forest.servlet.CatResponse;
import com.forest.servlet.CatServlet;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * @Author forest
 * @Date 2022/10/7 22:02
 * @Version 1.0
 */
@Log
public class CatServerHandler extends ChannelInboundHandlerAdapter {

    private static final String DefaultFilePath = "/index.html";

    private final Map<String, String> nameToClassNameMap;

    public CatServerHandler(Map<String, String> nameToClassNameMap) {
        this.nameToClassNameMap = nameToClassNameMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(ctx.toString());
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            CatHttpRequest heroRequest = new CatHttpRequest(request);

            log.info("Request path: " + heroRequest.getPath());
            log.info("Request parameters: " + heroRequest.getParameters().toString());

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK);

            // 默认情况会返回404
            CatServlet servlet = new DefaultCatServlet();

            // 读取请求的url中的path，匹配成本地的文件，然后返回给客户端

            CatRequest catRequest = new CatHttpRequest(request);
            CatResponse catResponse = new CatHttpResponse(ctx, response, request);

            String path = catRequest.getPath();
            String servletName = path.substring(path.lastIndexOf("/") + 1, path.length());

            String className = nameToClassNameMap.get(servletName);

            if (className != null) {
                servlet = (CatServlet) Class.forName(className).newInstance();
            } else {
                // 如果访问的是目录的根路径，尝试读取默认的HTML文件
                if (path.equals("/")) {
                    path = DefaultFilePath;
                }
                URL fileUrl = Thread.currentThread().getContextClassLoader().getResource(".");

                Path filePath = Paths.get(fileUrl.getPath(), path);
                File file = filePath.toFile();
                // 处理文件不存在的情况
                if (file.isHidden() || !file.exists()) {
                    servlet = new DefaultCatServlet();
                }
                else {
                    servlet = new StaticFileServlet(file);
                }
            }

            servlet.doGet(catRequest, catResponse);
        }

    }
}
