package com.forest.cat;

import com.forest.servlet.CatRequest;
import com.forest.servlet.CatResponse;
import com.forest.servlet.CatServlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.net.FileNameMap;
import java.net.URL;

/**
 * @Author forest
 * @Date 2022/10/9 23:44
 * @Version 1.0
 */
public class StaticFileServlet implements CatServlet {

    private final File file;

    public StaticFileServlet(File file) {
        this.file = file;
    }

    @Override
    public void doGet(CatRequest request, CatResponse response) throws Exception {
//        BufferedReader reader = new BufferedReader(new FileReader(url.getFile()));
//        String str;
//        StringBuilder res = new StringBuilder();
//        while ((str = reader.readLine()) != null) {
//            res.append(str);
//        }
//        response.write(res.toString());

        response.write(file);

    }

    @Override
    public void doPost(CatRequest request, CatResponse response) throws Exception {
        this.doGet(request, response);
    }
}
