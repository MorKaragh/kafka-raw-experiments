package stuff;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class StupidPartitioner implements Partitioner {

    Logger logger = LoggerFactory.getLogger(StupidPartitioner.class);

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return 0;
    }

    @Override
    public void close() {
        logger.info("Partitioner closed!");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        logger.info("Partitioner configured with map: " + configs);
    }
}
