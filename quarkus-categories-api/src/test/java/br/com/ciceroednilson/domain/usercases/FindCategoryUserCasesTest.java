package br.com.ciceroednilson.domain.usercases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class FindCategoryUserCasesTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private FindCategoryUserCases findCategoryUserCases;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFind() {
        long id = 1L;
        final CategoryEntity expectedEntity = new CategoryEntity();
        when(categoryRepository.findById(id)).thenReturn(expectedEntity);
        final CategoryEntity resultEntity = findCategoryUserCases.find(id);
        assertEquals(expectedEntity, resultEntity, "The returned category entity should match the expected entity.");
        verify(categoryRepository, times(1)).findById(id);

    }
}
