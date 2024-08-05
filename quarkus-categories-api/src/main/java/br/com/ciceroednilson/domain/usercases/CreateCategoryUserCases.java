package br.com.ciceroednilson.domain.usercases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import org.jboss.logging.Logger;

@Singleton
public class CreateCategoryUserCases {

    private static final Logger LOG = Logger.getLogger(CreateCategoryUserCases.class);
    private final CategoryRepository categoryRepository;

    @Inject
    public CreateCategoryUserCases(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create(final CategoryEntity categoryEntity) {
        LOG.info("creating a new category.");
        categoryEntity.setCreated(LocalDateTime.now());
        this.categoryRepository.create(categoryEntity);
        LOG.info("category created successfully.");
    }
}
