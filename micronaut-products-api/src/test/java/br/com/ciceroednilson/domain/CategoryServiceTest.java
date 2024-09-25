package br.com.ciceroednilson.domain;

import br.com.ciceroednilson.domain.service.CategoryService;
import br.com.ciceroednilson.infrastructure.client.CategoryClient;
import br.com.ciceroednilson.infrastructure.client.entity.Category;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import jakarta.inject.Inject;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@MicronautTest
class CategoryServiceTest {

    @Inject
    private CategoryClient categoryClient;

    @Inject
    private CategoryService categoryService;

    @MockBean(CategoryClient.class)
    CategoryClient categoryClient() {
        return Mockito.mock(CategoryClient.class);
    }

    Category buildCategory() {
        final Category mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setName("Games");
        return mockCategory;
    }

    @Test
    void testFindByIdCategoryExists() throws Exception {
        final Category mockCategory = buildCategory();
        when(categoryClient.findById(anyLong())).thenReturn(mockCategory);
        final Category category = categoryService.findById(1L);
        Assertions.assertNotNull(category);
        Assertions.assertEquals(mockCategory.getId(), category.getId());
        Assertions.assertEquals(mockCategory.getName(), category.getName());
    }

    @Test
    void testFindByIdCategoryNotFound() {
        when(categoryClient.findById(anyLong())).thenReturn(null);
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            categoryService.findById(1L);
        });
        final String messageCategoryNotFound = "Category not found!";
        Assertions.assertEquals(messageCategoryNotFound, exception.getMessage());
    }
}
