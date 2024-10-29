package br.com.ciceroednilson.application;

import br.com.ciceroednilson.application.request.OrderRequest;
import br.com.ciceroednilson.application.response.OrderResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/order")
public class OrderController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final OrderRequest orderRequest) {
        final String messageCreatedSuccess = "Order created successfully!";
        return Response.status(Response.Status.CREATED)
                .entity(messageCreatedSuccess)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        final String message = String.format("Order with id %s deleted successfully.", id);
        return Response.status(Response.Status.OK)
                .entity(message)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(new OrderResponse())
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(Long id) {
        return Response.status(Response.Status.OK)
                .entity(new OrderResponse())
                .build();
    }
}
