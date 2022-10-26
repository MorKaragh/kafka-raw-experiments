import data.avro.DataPiece;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stuff.MyConsumerInterceptor;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class MyConsumer {

    static Logger log = LoggerFactory.getLogger(MyConsumer.class);

    private volatile boolean keepConsuming = true;

    public static void main(String[] args) {
        Properties kaProps = new Properties();

        // даже если у нас много брокеров - тут можно указать один. Он расскажет нам об остальных.
        kaProps.put("bootstrap.servers", "localhost:9092");

        // группа consumer'ов
        kaProps.put("group.id", "experimental_consumer");

        // отключим автокоммит чтобы сдвигать offset после обработки, а не сразу при получении (вдруг упадем на обработке)
        kaProps.put("enable.auto.commit", "false");
        kaProps.put("auto.commit.interval.ms", "0");

        // если offset'а для нашей группы нет в природе - начинаем читать с первой записи в топике
        kaProps.put("auto.offset.reset", "earliest");

        // десереализация. Мы должны знать в каком формате приходят к нам сообщения
        kaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kaProps.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");

        // я игрался с форматом Avro, эти конфиги нужны для него
        kaProps.put("schema.registry.url", "http://localhost:8081");
        kaProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

        // перехватчик сообщений. В данном случае он ничего не делает, просто пишет в логи
        kaProps.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, MyConsumerInterceptor.class.getName());

        // период проверки consumer'а на необходимость ребаланса.
        // мало ли кто-то вывалится из группы и придется проводить ребаланс партиций.
        kaProps.put("heartbeat.interval.ms", "1000");


        MyConsumer consumer = new MyConsumer();
        consumer.consume(kaProps);
        Runtime.getRuntime().addShutdownHook(new Thread(consumer::shutdown));
    }

    private void consume(Properties props) {
        try (KafkaConsumer<String, DataPiece> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(List.of("TutorialTopic"));

            // ну почти while true
            while (keepConsuming) {

                // будем ждать ответа от kafka 1 секунду, блокирующая операция
                ConsumerRecords<String, DataPiece> records = consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, DataPiece> record : records) {

                    // вытащим значение из header'ов (я его прописываю в producer'е)
                    String messageTime = "";
                    if (record.headers().lastHeader("currtime") != null) {
                        messageTime = new String(record.headers().lastHeader("currtime").value());
                    }

                    // сделаем вид что обрабатываем сообщение
                    log.info(String.format("NEW MESSAGE! offset = %d value = %s time = %s",
                            record.offset(), record.value().getName(), messageTime));

                    // обработка успешна, можно коммитить. Помним, что autocommit у нас false.
                    // Можно делать и асинхронно, но это уже сами.
                    consumer.commitSync();
                }
            }
        }
    }

    private void shutdown() {
        keepConsuming = false;
    }

}
