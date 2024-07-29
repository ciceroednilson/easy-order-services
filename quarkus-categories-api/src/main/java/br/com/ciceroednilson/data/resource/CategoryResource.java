package br.com.ciceroednilson.data.resource;

import br.com.ciceroednilson.data.model.CategoryRequestModel;
import br.com.ciceroednilson.domain.usercases.CreateCategoryUserCases;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/category")
public class CategoryResource {

    private final CreateCategoryUserCases createCategoryUserCases;

    @Inject
    public CategoryResource(CreateCategoryUserCases createCategoryUserCases) {
        this.createCategoryUserCases = createCategoryUserCases;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CategoryRequestModel model) {
        this.createCategoryUserCases.create(model.toEntity());
        return Response.ok().build();
    }
}
