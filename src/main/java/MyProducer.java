import data.avro.DataPiece;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class MyProducer {

    static Logger log = LoggerFactory.getLogger(MyProducer.class);

    public static void main(String[] args) {
        Properties kaProps = new Properties();
        kaProps.put("bootstrap.servers", "localhost:9092");
        kaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kaProps.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        kaProps.put("schema.registry.url", "http://localhost:8081");
        kaProps.put("interceptor.classes", MyProducerInterceptor.class.getName());

        try (Producer<String, DataPiece> producer = new KafkaProducer<>(kaProps)) {
            int i = 0;
            while (true) {
                DataPiece dataPiece = new DataPiece("Name " + i, i, UUID.randomUUID().toString());
                ProducerRecord<String, DataPiece> record = new ProducerRecord<>("TutorialTopic", "RecKey", dataPiece);
                RecordMetadata recordMetadata = producer.send(record, new ProducerCallback()).get();
                log.info("RECORD SENT! offset=" + recordMetadata.offset() + " partition=" + recordMetadata.partition());
                Thread.sleep(2000);
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
        }
    }

}