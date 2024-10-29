package br.com.ciceroednilson.infrastructure.rest;

import br.com.ciceroednilson.domain.ports.ProductRestPort;
import br.com.ciceroednilson.infrastructure.rest.entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class ProductRest implements ProductRestPort {

    @Override
    public ProductEntity findById(final Long id) {
        try {
            final Client client = ClientBuilder.newClient();
            return client
                    .target(String.format("http://127.0.01:9000/product/%s", id))
                    .request(MediaType.APPLICATION_JSON)
                    .get(ProductEntity.class);
        } catch (final NotFoundException ex) {
            return null;
        }

    }
}
