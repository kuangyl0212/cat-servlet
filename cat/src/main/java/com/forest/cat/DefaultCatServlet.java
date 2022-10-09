package com.forest.cat;

import io.netty.handler.codec.http.HttpResponseStatus;
import com.forest.servlet.CatRequest;
import com.forest.servlet.CatResponse;
import com.forest.servlet.CatServlet;

/**
 * @Author forest
 * @Date 2022/10/9 20:21
 * @Version 1.0
 */
public class DefaultCatServlet implements CatServlet {
    @Override
    public void doGet(CatRequest request, CatResponse response) throws Exception {
        response.setStatus(HttpResponseStatus.NOT_FOUND);
        response.write("404 Not Found");
    }

    @Override
    public void doPost(CatRequest request, CatResponse response) throws Exception {
        this.doGet(request, response);
    }
}
