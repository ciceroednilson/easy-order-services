package br.com.ciceroednilson.domain.usecases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@QuarkusTest
public class CreateCategoryUseCasesTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CreateCategoryUseCases createCategoryUseCases;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        final LocalDateTime beforeCreate = LocalDateTime.now().minusMinutes(1);
        createCategoryUseCases.create(categoryEntity);
        assertTrue(categoryEntity.getCreated().isAfter(beforeCreate), "The created date should be set to current time.");
        verify(categoryRepository, times(1)).create(categoryEntity);
    }
}
