package top.itning.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * 工厂类
 *
 * @author itning
 * @date 2019/5/8 17:51
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {
    @Override
    public OrderEvent newInstance() {
        //这个方法就是返回空的数据对象(event)
        return new OrderEvent();
    }
}
