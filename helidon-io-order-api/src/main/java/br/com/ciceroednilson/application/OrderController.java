package br.com.ciceroednilson.application;

import br.com.ciceroednilson.application.request.OrderRequest;
import br.com.ciceroednilson.application.response.OrderResponse;
import br.com.ciceroednilson.domain.ports.OrderServicePort;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/order")
@RequestScoped
public class OrderController {

    private final OrderServicePort orderServicePort;

    @Inject
    public OrderController(final OrderServicePort orderServicePort) {
        this.orderServicePort = orderServicePort;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final OrderRequest orderRequest) {
        Map<String, String> mapResult = new HashMap<>();
        try {
            final String result = this.orderServicePort.create(orderRequest.toModel());
            mapResult.put("result", result);
            return Response.status(Response.Status.CREATED)
                    .entity(mapResult)
                    .build();
        } catch (final Exception ex) {
            mapResult.put("result", ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mapResult)
                    .build();
        }
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
