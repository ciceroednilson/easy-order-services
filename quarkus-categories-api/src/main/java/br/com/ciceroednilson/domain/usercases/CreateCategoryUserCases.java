package br.com.ciceroednilson.domain.usercases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

@Singleton
public class CreateCategoryUserCases {

    private static final Logger LOG = Logger.getLogger(CreateCategoryUserCases.class);
    private final CategoryRepository categoryRepository;

    @Inject
    public CreateCategoryUserCases(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void create(CategoryEntity categoryEntity) {
        LOG.info("OI");
        this.categoryRepository.create(categoryEntity);
    }
}
