package top.itning.disruptor.high;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 交易
 *
 * @author itning
 * @date 2019/5/9 21:27
 */
public class Trade {
    /**
     * id
     */
    private String id;
    /**
     * 交易名
     */
    private String name;
    /**
     * 价格
     */
    private double price;
    /**
     * 统计
     */
    private AtomicInteger count = new AtomicInteger(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
