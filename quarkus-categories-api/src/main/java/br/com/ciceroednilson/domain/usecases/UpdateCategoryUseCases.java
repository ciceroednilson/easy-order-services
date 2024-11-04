package br.com.ciceroednilson.domain.usecases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

@Singleton
public class UpdateCategoryUseCases {

    private static final Logger LOG = Logger.getLogger(UpdateCategoryUseCases.class);
    private final CategoryRepository categoryRepository;

    @Inject
    public UpdateCategoryUseCases(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void update(final CategoryEntity categoryEntity) {
        LOG.info(String.format("updating a category. Id: %d", categoryEntity.getIdCategory()));
        this.categoryRepository.update(categoryEntity);
        LOG.info("category updated successfully.");
    }
}
