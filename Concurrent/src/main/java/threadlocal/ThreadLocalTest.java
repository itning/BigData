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

    private static ThreadLocal<Integer> integerThreadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();
        new ThreadTest().start();
        stringThreadLocal.set("main value");
        System.out.println(stringThreadLocal.get() + " " + integerThreadLocal.get());
        //stringThreadLocal.remove();
    }

    static class ThreadTest extends Thread {
        @Override
        public void run() {
            if ("Thread-0".equals(Thread.currentThread().getName())) {
                integerThreadLocal.set(2);
            }
            System.out.println(stringThreadLocal.get() + " " + integerThreadLocal.get());
        }
    }
}
