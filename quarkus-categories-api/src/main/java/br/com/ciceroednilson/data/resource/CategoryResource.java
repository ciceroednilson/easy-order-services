package br.com.ciceroednilson.data.resource;

import br.com.ciceroednilson.data.model.CategoryRequestModel;
import br.com.ciceroednilson.data.model.CategoryResponseModel;
import br.com.ciceroednilson.domain.entities.CategoryEntity;
import br.com.ciceroednilson.domain.usercases.CreateCategoryUserCases;
import br.com.ciceroednilson.domain.usercases.DeleteCategoryUserCases;
import br.com.ciceroednilson.domain.usercases.FindCategoryUserCases;
import br.com.ciceroednilson.domain.usercases.SearchAllCategoriesUserCases;
import br.com.ciceroednilson.domain.usercases.UpdateCategoryUserCases;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    private final CreateCategoryUserCases createCategoryUserCases;
    private final UpdateCategoryUserCases updateCategoryUserCases;
    private final DeleteCategoryUserCases deleteCategoryUserCases;
    private final FindCategoryUserCases findCategoryUserCases;
    private final SearchAllCategoriesUserCases searchAllCategoriesUserCases;

    @Inject
    public CategoryResource(
            final CreateCategoryUserCases createCategoryUserCases,
            final UpdateCategoryUserCases updateCategoryUserCases,
            final DeleteCategoryUserCases deleteCategoryUserCases,
            final FindCategoryUserCases findCategoryUserCases,
            final SearchAllCategoriesUserCases searchAllCategoriesUserCases) {
        this.createCategoryUserCases = createCategoryUserCases;
        this.updateCategoryUserCases = updateCategoryUserCases;
        this.deleteCategoryUserCases = deleteCategoryUserCases;
        this.findCategoryUserCases = findCategoryUserCases;
        this.searchAllCategoriesUserCases = searchAllCategoriesUserCases;
    }

    @POST
    public Response create(CategoryRequestModel model) {
        this.createCategoryUserCases.create(model.toEntity());
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
        final List<CategoryEntity> list = this.searchAllCategoriesUserCases.searchAll();
        return CategoryEntity.toListModel(list);
    }
}
