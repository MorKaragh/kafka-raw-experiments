import data.avro.DataPiece;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Producer {

    public static void main(String[] args) {
        Properties kaProps = new Properties();
        kaProps.put("bootstrap.servers", "localhost:9092");
        kaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kaProps.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        kaProps.put("schema.registry.url", "http://localhost:8081");

        try (org.apache.kafka.clients.producer.Producer<String, DataPiece> producer
                     = new KafkaProducer<>(kaProps)) {
            for (int i = 0; i < 10; i++) {
                DataPiece dataPiece = new DataPiece();
                dataPiece.setName("Name " + i);
                dataPiece.setIntval(i);
                dataPiece.setTextval(UUID.randomUUID().toString());
                ProducerRecord<String, DataPiece> record = new ProducerRecord<>("TutorialTopic", dataPiece);
                RecordMetadata recordMetadata = producer.send(record, new ProducerCallback()).get();
                System.out.println("RECORD SENT! offset=" + recordMetadata.offset()
                        + " partition=" + recordMetadata.partition());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
