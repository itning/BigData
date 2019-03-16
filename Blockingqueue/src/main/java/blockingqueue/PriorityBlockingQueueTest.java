package blockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue 是一个支持优先级的无界阻塞队列。默认情况下元素采用自然顺序进行排序，
 * 也可以通过自定义类实现 compareTo() 方法来指定元素排序规则，
 * 或者初始化时通过构造器参数 Comparator 来指定排序规则。
 *
 * @author itning
 */
public class PriorityBlockingQueueTest {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        priorityBlockingQueue.add(3);
        priorityBlockingQueue.add(2);
        priorityBlockingQueue.add(6);
        for (Integer i : priorityBlockingQueue) {
            System.out.println(i);
        }
    }
}
