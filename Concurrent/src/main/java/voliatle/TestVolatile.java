package voliatle;

/**
 * volatile 测试
 *
 * @author itning
 */
@SuppressWarnings("all")
public class TestVolatile {
    private static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        // ++操作并不是原子操作
                        num++;
                    }
                }
            }).start();
        }
        Thread.sleep(2000);
        //并不是线程安全
        System.out.println(num);
    }
}
