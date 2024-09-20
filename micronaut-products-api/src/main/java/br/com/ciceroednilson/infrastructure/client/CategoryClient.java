package br.com.ciceroednilson.infrastructure.client;

import br.com.ciceroednilson.infrastructure.client.entity.Category;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;

@Client("${api.category.url}")
public interface CategoryClient {

    @Get("/{id}")
    Category findById(@PathVariable Long id);
}
