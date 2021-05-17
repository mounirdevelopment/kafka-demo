import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerExample {

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers","127.0.0.1:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        Producer producer = new KafkaProducer<String, String>(kafkaProps);

        ProducerRecord<String, String> record = new ProducerRecord<>("customers", "0110","yassine");

        try {

            producer.send(record);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
