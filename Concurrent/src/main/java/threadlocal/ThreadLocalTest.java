package threadlocal;

/**
 * main value
 * 初始化值
 * 初始化值
 * 初始化值
 * 初始化值
 *
 * @author itning
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "初始化值";
        }
    };

    public static void main(String[] args) {
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();
        stringThreadLocal.set("main value");
        System.out.println(stringThreadLocal.get());
        stringThreadLocal.remove();
    }

    static class ThreadTest extends Thread {
        @Override
        public void run() {
            System.out.println(stringThreadLocal.get());
        }
    }
}
