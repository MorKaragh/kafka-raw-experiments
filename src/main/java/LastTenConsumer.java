import data.avro.DataPiece;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class LastTenConsumer {

    Logger logger = LoggerFactory.getLogger(LastTenConsumer.class);

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

        LastTenConsumer consumer = new LastTenConsumer();
        consumer.consume(kaProps);
    }

    private void consume(Properties props) {

        TopicPartition topicPartition = new TopicPartition("TutorialTopic", 0);

        try (KafkaConsumer<String, DataPiece> consumer = new KafkaConsumer<>(props)) {

            // назначим consumer'у партицию и он сразу поймет где его offset
            consumer.assign(List.of(topicPartition));
            logger.info("Consumer group current offset:" + consumer.position(topicPartition));

            // просмотрим топик до конца
            consumer.seekToEnd(List.of(topicPartition));
            logger.info("Last offset in topic: " + consumer.position(topicPartition));

            // а теперь просмотрим последние 10 сообщений в топике
            consumer.seek(topicPartition, consumer.position(topicPartition) - 10);

            // извлечём данные, которые только что просмотрели
            ConsumerRecords<String, DataPiece> poll = consumer.poll(Duration.ofMillis(200));

            // и обработаем 10 последних сообщений
            for (ConsumerRecord<String, DataPiece> record : poll) {
                logger.info(record.key() + " " + record.value());
            }
        }
    }

}
