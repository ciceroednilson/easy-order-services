package br.com.ciceroednilson.domain.service;

import br.com.ciceroednilson.application.services.CategoryServiceProvider;
import br.com.ciceroednilson.infrastructure.client.CategoryClient;
import br.com.ciceroednilson.infrastructure.client.entity.Category;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CategoryService implements CategoryServiceProvider {

    protected CategoryClient categoryClient;

    @Inject
    public CategoryService(final CategoryClient categoryClient) {
        this.categoryClient = categoryClient;
    }

    @Override
    public Category findById(final Long id) throws Exception {
        final Category category = this.categoryClient.findById(id);
        if (category == null) {
            throw new Exception("Category not found!");
        }
        return category;
    }
}
