package br.com.ciceroednilson.domain.usecases;

import br.com.ciceroednilson.domain.repository.CategoryRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

@Singleton
public class DeleteCategoryUseCases {

    private static final Logger LOG = Logger.getLogger(DeleteCategoryUseCases.class);
    private final CategoryRepository categoryRepository;

    @Inject
    public DeleteCategoryUseCases(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void delete(final long id) {
        LOG.info(String.format("deleting a category. Id: %d", id));
        this.categoryRepository.delete(id);
        LOG.info("category deleted successfully.");
    }
}
