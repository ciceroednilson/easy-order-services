package br.com.ciceroednilson.domain.usercases;

import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.repository.CategoryRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class SearchAllCategoriesUserCasesTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private SearchAllCategoriesUserCases searchAllCategoriesUserCases;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchAll() {
        final CategoryEntity entity = new CategoryEntity();
        entity.setIdCategory(1L);
        final List<CategoryEntity> expectedCategories = List.of(entity);
        when(categoryRepository.searchAll()).thenReturn(expectedCategories);
        final List<CategoryEntity> resultCategories = searchAllCategoriesUserCases.searchAll();
        assertEquals(expectedCategories, resultCategories, "The returned category list should match the expected list.");
        verify(categoryRepository, times(1)).searchAll();
    }
}
