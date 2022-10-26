import data.avro.DataPiece;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.MyProducerInterceptor;
import stuff.ProducerCallback;
import stuff.StupidPartitioner;

import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class MyProducer {

    static Logger log = LoggerFactory.getLogger(MyProducer.class);

    public static void main(String[] args) {
        Properties kaProps = new Properties();

        // даже если у нас много брокеров - тут можно указать один. Он расскажет нам об остальных.
        kaProps.put("bootstrap.servers", "localhost:9092");

        // сериализация. Упаковываем наше сообщение в желаемый формат.
        kaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kaProps.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");

        // я игрался с форматом Avro, эти конфиги нужны для него
        kaProps.put("schema.registry.url", "http://localhost:8081");

        // перехватчик сообщений. В нем буду всем объектам переводить name в uppercase
        kaProps.put("interceptor.classes", MyProducerInterceptor.class.getName());

        // этой штукой будем определять в какую партицию отправить сообщение
        kaProps.put("partitioner.class", StupidPartitioner.class.getName());

        // чтобы не дублировались сообщения делаем producer идемпотентным
        kaProps.put("enable.idempotence", "true");

        // прежде чем записать себе успех дождёмся ответа ОК от всех реплик
        kaProps.put("acks", "all");

        // если что-то пошло не так - попробуем ещё три раза
        kaProps.put("retries", "3");

        // будем отправлять по одному сообщению за раз, т.к. у нас есть retries, и мы хотим сохранить порядок сообщений
        kaProps.put("max.in.flight.requests.per.connection", "1");

        try (Producer<String, DataPiece> producer = new KafkaProducer<>(kaProps)) {
            // цикл тут нужет только затем, чтобы отправить сто сообщений подряд
            for (int i = 0; i < 100; i++) {

                // наполнение сообщения
                String key = "RecKey " + i % 10;
                DataPiece value = new DataPiece("Name " + i, i, UUID.randomUUID().toString());
                List<Header> headers = List.of(new RecordHeader("currtime", String.valueOf(System.currentTimeMillis()).getBytes()));

                // объект сообщения
                ProducerRecord<String, DataPiece> record = new ProducerRecord<>(
                        "TutorialTopic",
                        null,
                        key,
                        value,
                        headers);

                // после отправки сообщения хочу вызвать callback
                ProducerCallback callback = new ProducerCallback();

                // отправка сообщения происходит асинхронно, но я у возвращаемого Future дёрну get()
                RecordMetadata recordMetadata = producer.send(record, callback).get();

                // после отправки нам вернутся метаданные о том, как и куда всё это легло
                log.info("RECORD SENT! offset=" + recordMetadata.offset() + " partition=" + recordMetadata.partition());
            }

        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
        }
    }

}
