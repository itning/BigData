package concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * <p>其实像AtomicBoolean或AtomicLong等这样的变量在多线程修改时，都存在ABA的问题。所谓ABA问题类似于下面伪代码：
 * <p>
 * <p>第一步 定义一个变量AtomicLong value值为1
 * <p>第二步 Thread1修改value为2
 * <p>第三步 Thread2修改value为1
 * <p>第四步 Thread3通过判断value==1，来检查value是否被修改过
 * <p>所以Thread3以为value没有被修改过，但实际已经被改过两次了，这就是ABA问题。
 * <p>因此AtomicStampedReference通过引入“版本”的概念，来解决上面的问题。
 *
 * @author itning
 * @date 2020/5/22 19:17
 * @see AtomicMarkableReferenceTest
 */
@SuppressWarnings("all")
public class AtomicStampedReferenceTest {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);
    final static AtomicInteger at = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    Integer m = money.getReference();
                    int stamp1 = money.getStamp();
                    if (m < 20 && at.get() < 2) {
                        // reference即我们实际存储的变量，stamp是版本，每次修改可以通过+1保证版本唯一性。
                        if (money.compareAndSet(m, m + 20, stamp1, stamp1 + 1)) {
                            System.out.println("余额小于20,充值成功,当前余额:" + money.getReference());
                            at.getAndIncrement();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        new Thread(() -> {
            while (true) {
                Integer m = money.getReference();
                int stamp = money.getStamp();
                if (m > 10) {
                    System.out.println("金额大于10:" + m);
                    if (money.compareAndSet(m, m - 10, stamp, stamp + 1)) {
                        System.out.println("成功消费10元,剩余金额:" + money.getReference());
                    }
                } else {
                    System.out.println("余额不足10元");
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
