package br.com.ciceroednilson.domain.ports;

import java.io.IOException;
import java.util.List;

public interface KafkaMessageProducerPort {

    void publishMessage(long order, List<Long> ids) throws IOException;
}
