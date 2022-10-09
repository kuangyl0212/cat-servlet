package com.forest.cat;

/**
 * @Author forest
 * @Date 2022/10/7 21:42
 * @Version 1.0
 */
public class CatRunner {
    public static void run(String[] args) {
        CatServer server = new CatServer(8080, "com.forest.webapp");
        server.start();
    }
}
