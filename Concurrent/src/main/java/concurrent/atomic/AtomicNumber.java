package concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic 翻译成中文是原子的意思。在化学上，我们知道原子是构成一般物质的最小单位，在化学反应中是不可分割的。
 * 在我们这里 Atomic 是指一个操作是不可中断的。即使是在多个线程一起执行的时候，一个操作一旦开始，就不会被其他线程干扰。
 * <p>
 * 所以，所谓原子类说简单点就是具有原子/原子操作特征的类。
 *
 * @author itning
 * @see java.util.concurrent.atomic.AtomicInteger
 * @see java.util.concurrent.atomic.AtomicBoolean
 * @see java.util.concurrent.atomic.AtomicLong
 * @see java.util.concurrent.atomic.AtomicReference
 * @see java.util.concurrent.atomic.AtomicIntegerArray
 * @see java.util.concurrent.atomic.AtomicLongArray
 * @see java.util.concurrent.atomic.AtomicReferenceArray
 */
public class AtomicNumber {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        System.out.println("获取当前的值: " + atomicInteger.get());
        System.out.println("获取当前的值，并设置新的值: " + atomicInteger.getAndSet(20));
        System.out.println("获取当前的值，并自增: " + atomicInteger.getAndIncrement());
        System.out.println("获取当前的值，并自减: " + atomicInteger.getAndDecrement());
        System.out.println("获取当前的值，并加上预期的值: " + atomicInteger.getAndAdd(5));
        //AtomicIntegerFieldUpdater<U> newUpdater = AtomicIntegerFieldUpdater.newUpdater(Class < U > tclass, String fieldName);
        //AtomicIntegerFieldUpdater:原子更新整形字段的更新器
        //AtomicLongFieldUpdater：原子更新长整形字段的更新器
        //AtomicStampedReference ：原子更新带有版本号的引用类型。
        // 该类将整数值与引用关联起来，可用于解决原子的更新数据和数据的版本号，可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。
    }
}
