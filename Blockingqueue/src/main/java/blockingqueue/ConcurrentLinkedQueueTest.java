package blockingqueue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 并发非阻塞队列
 *
 * @author itning
 */
public class ConcurrentLinkedQueueTest {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue.add("1");
        String peek = concurrentLinkedQueue.peek();
        System.out.println(peek);
    }
}
