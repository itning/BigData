package top.itning.reactor;

/**
 * @author itning
 * @since 2021/11/3 13:50
 */
public class EchoReactorSingleServerBootStrap {
    public static void main(String[] args) {
        Thread thread = new Thread(new EchoReactorSingleServer());
        thread.setDaemon(false);
        thread.start();
    }
}
