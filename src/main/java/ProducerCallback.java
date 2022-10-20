import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerCallback implements Callback {
    Logger logger = LoggerFactory.getLogger(ProducerCallback.class);

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            logger.error("Error in callback! topic=" + recordMetadata.topic()
                    + " time=" + recordMetadata.timestamp(), e);
        } else {
            logger.info("Callback OK! topic=" + recordMetadata.topic()
                    + " time=" + recordMetadata.timestamp());
        }
    }
}
