package br.com.ciceroednilson.domain.repository;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import java.util.List;

public interface CategoryRepository {

    void create(CategoryEntity categoryEntity);

    void delete(long id);

    void update(CategoryEntity categoryEntity);

    CategoryEntity findById(long id);

    List<CategoryEntity> searchAll();
}
