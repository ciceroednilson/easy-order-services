package br.com.ciceroednilson.domain.usercases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class UpdateCategoryUserCases {

    private static final Logger LOG = Logger.getLogger(UpdateCategoryUserCases.class);
    private final CategoryRepository categoryRepository;

    @Inject
    public UpdateCategoryUserCases(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void update(final CategoryEntity categoryEntity) {
        LOG.info(String.format("updating a category. Id: %d", categoryEntity.getIdCategory()));
        this.categoryRepository.update(categoryEntity);
        LOG.info("category updated successfully.");
    }
}
