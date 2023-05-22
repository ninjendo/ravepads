package com.rave.pads.controller;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rave.pads.sevice.CrawlerService;
import com.rave.pads.sevice.HudHomePropertyService;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import io.micronaut.test.annotation.MockBean;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HudControllerTest {

    private static MicronautLambdaHandler handler;
    private static Context lambdaContext = new MockLambdaContext();
    @Inject
    HudHomePropertyService service;

    @Prototype
    @Replaces(bean = CrawlerService.class)
    public class MockCrawlerService {
        public byte[] downloadResults() {
            return "1, 123 main st, Joe Plaza, GA".getBytes();
        }

    }

    @BeforeAll
    public static void setupSpec() {
        try {
            handler = new MicronautLambdaHandler();
        } catch (ContainerInitializationException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanupSpec() {
        handler.getApplicationContext().close();
    }

    @Test
    void testHelloHud() throws JsonProcessingException {
        AwsProxyRequest request = new AwsProxyRequest();
        request.setHttpMethod("GET");
        request.setPath("/hud/greet");
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\":\"Hello HUD\"}",  response.getBody());
    }
    @Test
    void testDownloadGA() throws JsonProcessingException {
        AwsProxyRequest request = new AwsProxyRequest();
        request.setHttpMethod("GET");
        request.setPath("/hud/properties/FL");
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\":\"Success download!\"}",  response.getBody());
    }

    @Test
    public void testHelloEndpoint(RequestSpecification spec) {
        spec
                .when()
                .get("/hud/properties/state/GA")
                .then()
                .statusCode(200)
                .body(is("Success download!"));
    }
}
