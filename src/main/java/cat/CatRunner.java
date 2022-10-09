package cat;

/**
 * @Author forest
 * @Date 2022/10/7 21:42
 * @Version 1.0
 */
public class CatRunner {
    public static void main(String[] args) {
        CatServer server = new CatServer(8080);
        server.start();
    }
}
