package br.com.ciceroednilson.domain.usecases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class UpdateCategoryUseCasesTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private UpdateCategoryUseCases updateCategoryUserCases;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdate() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setIdCategory(1L);
        updateCategoryUserCases.update(categoryEntity);
        verify(categoryRepository, times(1)).update(categoryEntity);
    }
}
