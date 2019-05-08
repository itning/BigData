package top.itning.rabbitmq.quickstart;

import com.rabbitmq.client.*;
import top.itning.rabbitmq.RabbitmqApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author itning
 * @date 2019/5/7 8:54
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqApplication.get();
        Channel channel = connection.createChannel();

        String queue = "test001";
        channel.queueDeclare(queue, true, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("consumerTag " + consumerTag);
            Object my1 = message.getProperties().getHeaders().get("my1");
            System.out.println(my1);
            System.out.println("message " + new String(message.getBody()));

            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);

            //channel.basicNack(message.getEnvelope().getDeliveryTag(), false, false);
        };

        CancelCallback cancelCallback = consumerTag -> System.out.println("consumerTag " + consumerTag);

        channel.basicQos(0, 3, false);

        channel.basicConsume(queue, false, deliverCallback, cancelCallback);
    }
}
