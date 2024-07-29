package br.com.ciceroednilson.data.repository;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public void create(CategoryEntity categoryEntity) {
        // TODO document why this method is empty
    }
}
