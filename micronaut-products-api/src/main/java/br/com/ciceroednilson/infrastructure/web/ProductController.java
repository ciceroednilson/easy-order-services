package br.com.ciceroednilson.infrastructure.web;


import br.com.ciceroednilson.application.mapper.ProductMapper;
import br.com.ciceroednilson.application.services.ProductServiceProvider;
import br.com.ciceroednilson.domain.model.ProductModel;
import br.com.ciceroednilson.infrastructure.web.request.ProductRequest;
import br.com.ciceroednilson.infrastructure.web.response.ProductResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

import java.util.List;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/product")
public class ProductController {

    ProductServiceProvider productServiceProvider;

    @Inject
    public ProductController(ProductServiceProvider productServiceProvider) {
        this.productServiceProvider = productServiceProvider;
    }

    @Post(uri = "/", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Void> save(@Body ProductRequest request) throws Exception {
        final ProductModel model = ProductMapper.toModel(request);
        this.productServiceProvider.save(model);
        return HttpResponse.ok();
    }

    @Put("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Void> update(@Body ProductRequest request, @PathVariable Long id) {
        final ProductModel model = ProductMapper.toModel(request);
        model.setId(id);
        this.productServiceProvider.update(model);
        return HttpResponse.ok();
    }

    @Delete("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Void> delete(@PathVariable Long id) {
        this.productServiceProvider.delete(id);
        return HttpResponse.ok();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<ProductResponse> findById(@PathVariable Long id) {
        final ProductModel model = productServiceProvider.findById(id);
        return model != null ? HttpResponse.ok(ProductMapper.toResponse(model)) : HttpResponse.notFound();
    }

    @Get("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<ProductResponse>> findAll() {
        List<ProductModel> products = productServiceProvider.findAll();
        return HttpResponse.ok(ProductMapper.toListResponse(products));
    }

    @Put("/{id}/reduce/stock/")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Void> reduceStock(@PathVariable Long id) {
        this.productServiceProvider.reduceStock(id);
        return HttpResponse.ok();
    }
}
