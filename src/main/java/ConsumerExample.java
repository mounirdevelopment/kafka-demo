

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerExample {

    public static void main(String[] args) {

        Duration duration = Duration.ofMillis(100);

        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers","127.0.0.1:9092");
        kafkaProps.put("group.id","customers-01");
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        Consumer consumer = new KafkaConsumer<String, String>(kafkaProps);
        consumer.subscribe(Collections.singleton("customers"));

        while (true) {

            ConsumerRecords<String, String> records = consumer.poll(duration);

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic = %s, partition = %d, offset = %d, id = %s, name = %s\n",
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        record.key(),
                        record.value());
            }
        }
    }
}
