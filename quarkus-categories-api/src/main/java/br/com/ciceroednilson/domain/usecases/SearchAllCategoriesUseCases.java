package br.com.ciceroednilson.domain.usecases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import org.jboss.logging.Logger;

@Singleton
public class SearchAllCategoriesUseCases {

    private static final Logger LOG = Logger.getLogger(SearchAllCategoriesUseCases.class);
    private final CategoryRepository categoryRepository;

    @Inject
    public SearchAllCategoriesUseCases(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> searchAll() {
        LOG.info("searching all categories.");
        return this.categoryRepository.searchAll();
    }
}
