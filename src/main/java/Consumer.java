import data.avro.DataPiece;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Consumer {

    private volatile boolean keepConsuming = true;

    public static void main(String[] args) {
        Properties kaProps = new Properties();
        kaProps.put("bootstrap.servers", "localhost:9092");
        kaProps.put("group.id", "experimental_consumer");
        kaProps.put("enable.auto.commit", "false");
        kaProps.put("auto.commit.interval.ms", "1000");
        kaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kaProps.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        kaProps.put("schema.registry.url", "http://localhost:8081");

        kaProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

        Consumer consumer = new Consumer();
        consumer.consume(kaProps);

        Runtime.getRuntime().addShutdownHook(new Thread(consumer::shutdown));

    }

    private void consume(Properties props) {
        try (KafkaConsumer<String, DataPiece> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(List.of("TutorialTopic"));
            while (keepConsuming) {
                ConsumerRecords<String, DataPiece> records = consumer.poll(Duration.ofMillis(200));
                for (ConsumerRecord<String, DataPiece> record : records) {
                    System.out.println(String.format("offset = %d value = %s", record.offset(), record.value().getName()));
                }
            }
        }
    }

    private void shutdown() {
        keepConsuming = false;
    }

}
