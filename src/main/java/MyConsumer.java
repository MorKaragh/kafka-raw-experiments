import data.avro.DataPiece;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class MyConsumer {

    static Logger log = LoggerFactory.getLogger(MyConsumer.class);

    private volatile boolean keepConsuming = true;

    public static void main(String[] args) {
        Properties kaProps = new Properties();
        kaProps.put("bootstrap.servers", "localhost:9092");
        kaProps.put("group.id", "experimental_consumer");
        kaProps.put("heartbeat.interval.ms", "1000");
        kaProps.put("enable.auto.commit", "false");
        kaProps.put("auto.commit.interval.ms", "0");
        kaProps.put("auto.offset.reset", "none");
        kaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kaProps.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        kaProps.put("schema.registry.url", "http://localhost:8081");
        kaProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

        MyConsumer consumer = new MyConsumer();
        consumer.consume(kaProps);

        Runtime.getRuntime().addShutdownHook(new Thread(consumer::shutdown));

    }

    private void consume(Properties props) {
        try (KafkaConsumer<String, DataPiece> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(List.of("TutorialTopic"));
            while (keepConsuming) {
                ConsumerRecords<String, DataPiece> records = consumer.poll(Duration.ofMillis(200));
                for (ConsumerRecord<String, DataPiece> record : records) {
                    log.info(String.format("NEW MESSAGE! offset = %d value = %s", record.offset(), record.value().getName()));
                    consumer.commitSync();
                }
            }
        }
    }

    private void shutdown() {
        keepConsuming = false;
    }

}
