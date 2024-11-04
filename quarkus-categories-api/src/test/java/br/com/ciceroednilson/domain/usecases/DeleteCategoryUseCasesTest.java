package br.com.ciceroednilson.domain.usecases;

import br.com.ciceroednilson.domain.repository.CategoryRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.jboss.logging.Logger;

import static org.mockito.Mockito.*;

@QuarkusTest
public class DeleteCategoryUseCasesTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DeleteCategoryUseCases deleteCategoryUserCases;

    @Mock
    private Logger logger;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDelete() {
        final long id = 1L;
        deleteCategoryUserCases.delete(id);
        verify(categoryRepository, times(1)).delete(id);
    }
}
