package br.com.ciceroednilson.data.resource;

import br.com.ciceroednilson.data.model.CategoryRequestModel;
import br.com.ciceroednilson.data.model.CategoryResponseModel;
import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.usecases.CreateCategoryUseCases;

import br.com.ciceroednilson.domain.usecases.DeleteCategoryUseCases;
import br.com.ciceroednilson.domain.usecases.FindCategoryUseCases;
import br.com.ciceroednilson.domain.usecases.SearchAllCategoriesUseCases;
import br.com.ciceroednilson.domain.usecases.UpdateCategoryUseCases;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    private final CreateCategoryUseCases createCategoryUseCases;
    private final UpdateCategoryUseCases updateCategoryUserCases;
    private final DeleteCategoryUseCases deleteCategoryUserCases;
    private final FindCategoryUseCases findCategoryUserCases;
    private final SearchAllCategoriesUseCases searchAllCategoriesUseCases;

    @Inject
    public CategoryResource(
            final CreateCategoryUseCases createCategoryUseCases,
            final UpdateCategoryUseCases updateCategoryUserCases,
            final DeleteCategoryUseCases deleteCategoryUserCases,
            final FindCategoryUseCases findCategoryUserCases,
            final SearchAllCategoriesUseCases searchAllCategoriesUseCases) {
        this.createCategoryUseCases = createCategoryUseCases;
        this.updateCategoryUserCases = updateCategoryUserCases;
        this.deleteCategoryUserCases = deleteCategoryUserCases;
        this.findCategoryUserCases = findCategoryUserCases;
        this.searchAllCategoriesUseCases = searchAllCategoriesUseCases;
    }

    @POST
    public Response create(CategoryRequestModel model) {
        this.createCategoryUseCases.create(model.toEntity());
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(final @PathParam("id") Long id, final CategoryRequestModel model) {
        model.setId(id);
        this.updateCategoryUserCases.update(model.toEntity());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(final @PathParam("id") String id) {
        this.deleteCategoryUserCases.delete(Long.parseLong(id));
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public CategoryResponseModel find(final @PathParam("id") Long id) {
        return this.findCategoryUserCases.find(id).toModel();
    }

    @GET
    @Path("/all")
    public List<CategoryResponseModel> finAll() {
        final List<CategoryEntity> list = this.searchAllCategoriesUseCases.searchAll();
        return CategoryEntity.toListModel(list);
    }
}
