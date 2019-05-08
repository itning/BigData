package top.itning.rabbitmq.quickstart;

import com.rabbitmq.client.*;
import top.itning.rabbitmq.RabbitmqApplication;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

/**
 * @author itning
 * @date 2019/5/7 9:14
 */
public class Procuder {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqApplication.get();

        Channel channel = connection.createChannel();

        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(String.format("handleAck: %s %s", deliveryTag, multiple));
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println(String.format("handleNack: %s %s", deliveryTag, multiple));
            }
        });

        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.out.println(String.format("%s %s %s %s", replyCode, replyText, exchange, routingKey));
            System.out.println(new String(body));
        });

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2)
                .contentEncoding("utf-8")
                .headers(Collections.singletonMap("my1", "val"))
                .build();
        channel.basicPublish("", "test001", properties, "测试消息".getBytes());

        /*channel.close();
        connection.close();*/
    }
}
