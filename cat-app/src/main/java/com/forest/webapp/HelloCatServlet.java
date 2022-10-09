package com.forest.webapp;

import io.netty.handler.codec.http.HttpResponseStatus;
import com.forest.servlet.CatRequest;
import com.forest.servlet.CatResponse;
import com.forest.servlet.CatServlet;

/**
 * @Author forest
 * @Date 2022/10/9 21:41
 * @Version 1.0
 */
public class HelloCatServlet implements CatServlet {
    @Override
    public void doGet(CatRequest request, CatResponse response) throws Exception {
        response.setStatus(HttpResponseStatus.OK);
        response.write("Hello, world!");
    }

    @Override
    public void doPost(CatRequest request, CatResponse response) throws Exception {
        this.doGet(request, response);
    }
}
