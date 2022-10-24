import data.avro.DataPiece;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class InformationConsumer {

    Logger logger = LoggerFactory.getLogger(InformationConsumer.class);

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

        InformationConsumer consumer = new InformationConsumer();
        consumer.consume(kaProps);
    }

    private void consume(Properties props) {
        TopicPartition topicPartition = new TopicPartition("TutorialTopic", 0);
        try (KafkaConsumer<String, DataPiece> consumer = new KafkaConsumer<>(props)) {
            consumer.assign(List.of(topicPartition));
            logger.info("Consumer group current offset:" + consumer.position(topicPartition));
            logger.info("Last offset in topic: " + consumer.endOffsets(List.of(topicPartition)));
        }
    }

}
