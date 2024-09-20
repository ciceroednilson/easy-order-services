package br.com.ciceroednilson.application.services;

import br.com.ciceroednilson.infrastructure.client.entity.Category;

public interface CategoryServiceProvider {

    Category findById(Long id) throws Exception;
}
