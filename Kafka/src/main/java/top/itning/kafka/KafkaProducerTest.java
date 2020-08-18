package top.itning.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wangn
 */
public class KafkaProducerTest {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerTest.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String topic = "test_topic";

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.66.3:9092,192.168.66.4:9092,192.168.66.5:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int messageNo = 1; messageNo < 100000; messageNo++) {
            // 5、调用producer的send方法发送数据
            // 注意：这里需要指定 partitionKey，用来配合自定义的MyLogPartitioner进行数据分发
            Thread.sleep(1);
            logger.info("send：{}", messageNo);
            // 异步发送
            producer.send(new ProducerRecord<>(topic, messageNo + ""), (metadata, exception) -> {
                if (null == exception) {
                    logger.info(metadata.toString());
                } else {
                    exception.printStackTrace();
                }
            });
            // 同步发送
            Future<RecordMetadata> send = producer.send(new ProducerRecord<>(topic, messageNo + ""));
            RecordMetadata recordMetadata = send.get();
            logger.info("done：{}", recordMetadata);
        }
        producer.flush();
        producer.close();
    }
}
