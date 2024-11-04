package br.com.ciceroednilson.domain.usecases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

@Singleton
public class FindCategoryUseCases {

    private static final Logger LOG = Logger.getLogger(FindCategoryUseCases.class);
    private final CategoryRepository categoryRepository;

    @Inject
    public FindCategoryUseCases(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity find(final long id) {
        LOG.info("finding a category.");
        return this.categoryRepository.findById(id);
    }
}
