package top.itning.disruptor.multi;

/**
 * @author itning
 * @date 2019/5/10 9:38
 */
public class Order {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                '}';
    }
}
