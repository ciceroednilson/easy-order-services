package br.com.ciceroednilson.data.resource;

import br.com.ciceroednilson.data.model.CategoryRequestModel;
import br.com.ciceroednilson.data.model.CategoryResponseModel;
import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.usercases.CreateCategoryUserCases;
import br.com.ciceroednilson.domain.usercases.DeleteCategoryUserCases;
import br.com.ciceroednilson.domain.usercases.FindCategoryUserCases;
import br.com.ciceroednilson.domain.usercases.SearchAllCategoriesUserCases;
import br.com.ciceroednilson.domain.usercases.UpdateCategoryUserCases;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@QuarkusTest
public class CategoryResourceTest {

    @Mock
    CreateCategoryUserCases createCategoryUserCases;

    @Mock
    UpdateCategoryUserCases updateCategoryUserCases;

    @Mock
    DeleteCategoryUserCases deleteCategoryUserCases;

    @Mock
    FindCategoryUserCases findCategoryUserCases;

    @Mock
    SearchAllCategoriesUserCases searchAllCategoriesUserCases;

    @InjectMocks
    @Inject
    CategoryResource categoryResource;

    @Test
    public void testCreateCategory() {
        CategoryRequestModel requestModel = new CategoryRequestModel();
        // Configure the requestModel as needed

        Response response = categoryResource.create(requestModel);

        verify(createCategoryUserCases).create(requestModel.toEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testUpdateCategory() {
        Long id = 1L;
        CategoryRequestModel requestModel = new CategoryRequestModel();
        requestModel.setId(id);

        Response response = categoryResource.update(id, requestModel);

        verify(updateCategoryUserCases).update(requestModel.toEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteCategory() {
        String id = "1";

        Response response = categoryResource.delete(id);

        verify(deleteCategoryUserCases).delete(Long.parseLong(id));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testFindCategory() {
        Long id = 1L;
        CategoryEntity entity = new CategoryEntity(); // Configure as needed
        CategoryResponseModel responseModel = new CategoryResponseModel(); // Configure as needed
        when(findCategoryUserCases.find(id)).thenReturn(entity);

        CategoryResponseModel result = categoryResource.find(id);

        assertEquals(responseModel, result);
        verify(findCategoryUserCases).find(id);
    }

    @Test
    public void testFindAllCategories() {
        CategoryEntity entity = new CategoryEntity(); // Configure as needed
        List<CategoryEntity> entities = Collections.singletonList(entity);
        List<CategoryResponseModel> responseModels = CategoryEntity.toListModel(entities);

        when(searchAllCategoriesUserCases.searchAll()).thenReturn(entities);

        List<CategoryResponseModel> result = categoryResource.finAll();

        assertEquals(responseModels, result);
        verify(searchAllCategoriesUserCases).searchAll();
    }
}
