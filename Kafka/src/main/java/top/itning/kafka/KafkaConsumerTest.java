package top.itning.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

/**
 * @author wangn
 */
public class KafkaConsumerTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.66.3:9092,192.168.66.4:9092,192.168.66.5:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 自动commit
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 自动commit的间隔
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        KafkaConsumer<Object, Object> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList("first"));
        while (true) {
            ConsumerRecords<Object, Object> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<Object, Object> record : records) {
                System.out.printf("partition = %d, topic = %s, offset = %d, key = %s, value = %s \n",
                        record.partition(), record.topic(), record.offset(), record.key(), record.value());
                System.out.println(new String((byte[]) record.value()));
            }
        }
    }
}
