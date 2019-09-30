package concurrent.threadrandom;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author itning
 */
public class ThreadRandomTest {
    public static void main(String[] args) {
        System.out.println(ThreadLocalRandom.current().nextInt());
        // 返回指定原点（含）和指定边界（排除）之间的伪随机 int值。
        System.out.println(ThreadLocalRandom.current().nextInt(0,100));
    }
}
