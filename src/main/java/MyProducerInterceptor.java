import data.avro.DataPiece;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MyProducerInterceptor implements ProducerInterceptor<String, DataPiece> {

    Logger logger = LoggerFactory.getLogger(MyProducerInterceptor.class);

    @Override
    public ProducerRecord<String, DataPiece> onSend(ProducerRecord<String, DataPiece> producerRecord) {
        if (producerRecord.value() == null || producerRecord.value().getName() == null) {
            return producerRecord;
        }
        producerRecord.value().setName(producerRecord.value().getTextval().toString().toUpperCase());
        logger.info("Intercepted and name changed to " + producerRecord.value().getName());
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            logger.error("Exception in interceptor!", e);
        }
        logger.info("Acked and has metadata " + recordMetadata.toString());
    }

    @Override
    public void close() {
        logger.info("Closed да и хуй с ним");
    }

    @Override
    public void configure(Map<String, ?> map) {
        logger.info("configured with map " + map.toString());
    }
}
