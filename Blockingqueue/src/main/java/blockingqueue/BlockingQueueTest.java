package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列测试
 *
 * @author wangn
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        //一个由数组支持的有界阻塞队列
        //规定大小的BlockingQueue,其构造函数必须带一个int参数来指明其大小.其所含的对象是以FIFO(先入先出)顺序排序的。
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        //大小不定的BlockingQueue,
        //若其构造函数带一个规定大小的参数,生成的BlockingQueue有大小限制,若不带大小参数,所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定.其所含的对象是以FIFO(先入先出)顺序排序的。
        LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
        //插入->
        //add(anObject):把anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则抛出异常,不好
        arrayBlockingQueue.add("add方法加入");
        //offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则返回false.
        //该方法有重载方法: public boolean offer(E e, long timeout, TimeUnit unit)
        arrayBlockingQueue.offer("Offer");
        //put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续, 有阻塞, 放不进去就等待
        try {
            arrayBlockingQueue.put("Put");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //读取->
        //poll(time):取走BlockingQueue里排在首位的对象,若不能立即取出,则可以等time参数规定的时间,取不到时返回null; 取不到返回null
        try {
            arrayBlockingQueue.poll(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到Blocking有新的对象被加入为止; 阻塞, 取不到就一直等
        try {
            arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //其他方法->
        //返回队列剩余的容量，在队列插入和获取的时候，数据可能不准, 不能保证数据的准确性
        System.out.println(arrayBlockingQueue.remainingCapacity());
        /*public boolean contains(Object o); 查看队列是否存在这个元素，存在返回true
        int drainTo(Collection<? super E> c); //移除此队列中所有可用的元素,并将它们添加到给定 collection 中。取出放到集合中
        int drainTo(Collection<? super E> c, int maxElements); 和上面方法的区别在于，指定了移	动的数量; 取出指定个数放到集合
        */
    }
}
