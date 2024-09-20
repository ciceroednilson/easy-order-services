package br.com.ciceroednilson;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
    info = @Info(
            title = "micronaut-products-api",
            version = "1.0"
    )
)
public class Application {
    //https://www.infoq.com/br/articles/onion-architecture/
 //https://medium.com/@abhigyan150/peeling-back-the-layers-demystifying-onion-architecture-for-modern-software-development-67c054ac5570
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}