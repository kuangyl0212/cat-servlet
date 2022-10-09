package servlet;

/**
 * @Author forest
 * @Date 2022/9/24 23:07
 * @Version 1.0
 */
public interface CatServlet {
    public abstract void doGet(CatRequest request, CatResponse response)
            throws Exception;
    public abstract void doPost(CatRequest request, CatResponse response)
            throws Exception;
}
