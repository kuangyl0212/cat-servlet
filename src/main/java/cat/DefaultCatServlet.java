package cat;

import servlet.CatRequest;
import servlet.CatResponse;
import servlet.CatServlet;

/**
 * @Author forest
 * @Date 2022/10/9 20:21
 * @Version 1.0
 */
public class DefaultCatServlet implements CatServlet {
    @Override
    public void doGet(CatRequest request, CatResponse response) throws Exception {
        response.write("Hello world!");
    }

    @Override
    public void doPost(CatRequest request, CatResponse response) throws Exception {
        response.write("Hello world!");
    }
}
