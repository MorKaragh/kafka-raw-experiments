package stuff;

import data.avro.DataPiece;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MyConsumerInterceptor implements ConsumerInterceptor<String, DataPiece> {

    Logger logger = LoggerFactory.getLogger(MyConsumerInterceptor.class);

    @Override
    public ConsumerRecords<String, DataPiece> onConsume(ConsumerRecords<String, DataPiece> records) {
        logger.info("Recieved records in interceptor! count=" + records.count());
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        logger.info("Commit intercepted! " + offsets);
    }

    @Override
    public void close() {
        logger.info("Consumer closing intercepted");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        logger.info("Consumer configured with " + configs);
    }
}
