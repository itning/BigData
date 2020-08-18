package top.itning.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author wangn
 */
public class KafkaProducerTest {

    public static void main(String[] args) throws InterruptedException {
        String topic = "first";

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.66.3:9092,192.168.66.4:9092,192.168.66.5:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int messageNo = 1; messageNo < 100000; messageNo++) {
            // 5、调用producer的send方法发送数据
            // 注意：这里需要指定 partitionKey，用来配合自定义的MyLogPartitioner进行数据分发
            Thread.sleep(1);
            System.out.println("send: " + messageNo);
            producer.send(new ProducerRecord<>(topic, messageNo + ""));
        }
        producer.flush();
        producer.close();
    }
}
