package com.rave.pads;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;

import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
        info = @Info(
                title = "RAVe Lead Funnelling System",
                description = "RAVE API",
                version = "0.1",
                contact = @Contact(url = "htp://ninjendo.com", name = "Jett", email = "jettish@gmail.com")
        )
)
public class Application extends MicronautLambdaHandler {

    public Application() throws ContainerInitializationException {
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}