import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

public class ProducerExample {

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers","127.0.0.1:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        Producer producer = new KafkaProducer<String, String>(kafkaProps);

        Date now = new Date();

        int i = 0;
        while (i < 100) {

            ProducerRecord<String, String> record = new ProducerRecord("customers", i, now.getTime(), "222"+i, "yassine"+i);

            try {

                producer.send(record);

            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        producer.flush();
    }
}
