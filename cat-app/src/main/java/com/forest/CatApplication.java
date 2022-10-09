package com.forest;

import com.forest.cat.CatRunner;
import com.forest.cat.CatServer;

/**
 * @Author ${USER}
 * @Date ${DATE} ${TIME}
 * @Version 1.0
 */
public class CatApplication {
    public static void main(String[] args) {
        CatServer server = null;
        try {
            server = new CatServer(8080, "com.forest.webapp");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        server.start();
    }
}