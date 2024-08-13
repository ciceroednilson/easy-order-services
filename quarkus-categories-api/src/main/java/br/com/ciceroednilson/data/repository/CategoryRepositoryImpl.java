package br.com.ciceroednilson.data.repository;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategoryRepositoryImpl implements CategoryRepository {

    final NotFoundException exception = new NotFoundException("Category not found!");

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @Override
    public void create(final CategoryEntity categoryEntity) {
        categoryEntity.persist();
    }

    @Transactional
    @Override
    public void delete(final long id) {
        final CategoryEntity category = findById(id);
        category.delete();
    }

    @Transactional
    @Override
    public void update(final CategoryEntity categoryEntity) {
        final CategoryEntity category = findById(categoryEntity.getIdCategory());
        category.setName(categoryEntity.getName());
        category.setActive(categoryEntity.getActive());
        category.setCreated(categoryEntity.getCreated());
        category.persist();
    }

    @Transactional
    @Override
    public CategoryEntity findById(final long id) {
        final Optional<CategoryEntity> optCategoryEntity =
                CategoryEntity.findByIdOptional(
                        id,
                        LockModeType.PESSIMISTIC_WRITE);
        return optCategoryEntity.orElseThrow(() -> exception);
    }

    @Override
    public List<CategoryEntity> searchAll() {
        return CategoryEntity.findAll().list();
    }
}
