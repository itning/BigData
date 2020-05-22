package concurrent.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * <p>AtomicMarkableReference可以理解为上面AtomicStampedReference的简化版，
 * <p>就是不关心修改过几次，仅仅关心是否修改过。
 * <p>因此变量mark是boolean类型，仅记录值是否有过修改。
 *
 * @author itning
 * @date 2020/5/22 19:27
 * @see AtomicStampedReferenceTest
 */
public class AtomicMarkableReferenceTest {
    private static final Integer A = 1;
    private static final AtomicMarkableReference<Integer> ATOMIC_MARKABLE_REFERENCE = new AtomicMarkableReference<>(A, false);

    public static void main(String[] args) {
        ATOMIC_MARKABLE_REFERENCE.compareAndSet(A, 2, false, true);
        System.out.println(ATOMIC_MARKABLE_REFERENCE.getReference());
    }
}
