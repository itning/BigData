package top.itning.disruptor.quickstart;

/**
 * 订单
 *
 * @author itning
 * @date 2019/5/8 17:50
 */
public class OrderEvent {
    /**
     * 价格
     */
    private long price;

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "price=" + price +
                '}';
    }
}
