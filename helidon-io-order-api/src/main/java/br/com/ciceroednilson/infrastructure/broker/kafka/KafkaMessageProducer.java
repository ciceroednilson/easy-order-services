package br.com.ciceroednilson.infrastructure.broker.kafka;

import br.com.ciceroednilson.domain.ports.KafkaMessageProducerPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@ApplicationScoped
public class KafkaMessageProducer implements KafkaMessageProducerPort {

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageProducer.class);
    @Inject
    @ConfigProperty(name = "kafka.broker")
    private String broker;

    @Inject
    @ConfigProperty(name = "kafka.topic")
    private String topic;

    KafkaProducer<String, String> producer;

    private void startConfig() {
        final Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", broker);
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    @Override
    public void publishMessage(final long order, final List<Long> ids) throws IOException {
        this.startConfig();
        try {
            final String message = ids
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, String.valueOf(order), message);
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata metadata = future.get();
            log.info(String.format("Message sent to topic %s partition %d with offset %d%n",
                    metadata.topic(), metadata.partition(), metadata.offset()));
        } catch (Exception ex) {
            throw new IOException(ex.toString());
        }
        finally {
            producer.close();
        }
    }
}
