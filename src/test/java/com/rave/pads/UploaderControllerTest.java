package com.rave.pads;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploaderControllerTest {

    private static MicronautLambdaHandler handler;
    private static Context lambdaContext = new MockLambdaContext();

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
    void testHandler() throws JsonProcessingException {
        AwsProxyRequest request = new AwsProxyRequest();
        request.setHttpMethod("GET");
        request.setPath("/ai-trainor");
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\":\"Welcome to AI Trainor.\"}",  response.getBody());
    }

//    @Test
//    void testRequestUpload() throws JsonProcessingException {
//        AwsProxyRequest request = new AwsProxyRequest();
//        request.setHttpMethod("GET");
//        request.setPath("/ai-trainor/request-upload/pdf/myresume.pdf");
//        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
//        assertEquals(200, response.getStatusCode());
//        assertTrue(response.getBody().contains("{\"message\":\"presignedUrl: https://ravepads-uploaded-files-bucket.s3.amazonaws.com/myresumepdf"));
//    }
}