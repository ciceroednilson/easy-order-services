package br.com.ciceroednilson.domain.ports;

import br.com.ciceroednilson.domain.model.OrderModel;
import javassist.NotFoundException;

public interface OrderServicePort {

    String create(final OrderModel model) throws Exception;
}
