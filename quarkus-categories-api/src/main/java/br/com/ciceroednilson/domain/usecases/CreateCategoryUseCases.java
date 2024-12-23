package br.com.ciceroednilson.domain.usecases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import org.jboss.logging.Logger;

@Singleton
public class CreateCategoryUseCases {

    private static final Logger LOG = Logger.getLogger(CreateCategoryUseCases.class);
    private final CategoryRepository categoryRepository;

    @Inject
    public CreateCategoryUseCases(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create(final CategoryEntity categoryEntity) {
        LOG.info("creating a new category.");
        categoryEntity.setCreated(LocalDateTime.now());
        this.categoryRepository.create(categoryEntity);
        LOG.info("category created successfully.");
    }
}
