package com.rave.pads;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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

    @Test
    void testRequestUpload() throws JsonProcessingException {
        AwsProxyRequest request = new AwsProxyRequest();
        request.setHttpMethod("GET");
        request.setPath("/ai-trainor/request-upload/pdf/myresume.pdf");
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().contains("{\"message\":\"https://ravepads-uploaded-files-bucket.s3.amazonaws.com/myresume.pdf"));
    }

    @Test
    void testUploadFiletoAWSS3() throws IOException {

        //Generate presignedURL
        AwsProxyRequest request = new AwsProxyRequest();
        request.setHttpMethod("GET");
        request.setPath("/ai-trainor/request-upload/pdf/myresume.pdf");
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        System.out.println(response.getBody());
        assertEquals(200, response.getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        String presignedURL = "";
        try {
            Map<String, String> map = objectMapper.readValue(response.getBody(), new TypeReference<Map<String,String>>(){});
            System.out.println(map);
            presignedURL = map.get("message");
            System.out.println("presignedURL==="+presignedURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Upload file to presignedURL
        File file = new File("C:\\Users\\arthu\\workspace\\ravepads\\myresume.pdf");
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(
                        RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()
                ).build();
        HttpPut put = new HttpPut(presignedURL);
        HttpEntity entity = EntityBuilder.create()
                .setFile(file)
                .build();
        put.setEntity(entity);
        put.setHeader("Content-Type","application/pdf");

        HttpResponse httpResponse = httpClient.execute(put);

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            System.out.println("File uploaded successfully at destination.");
        } else {
            System.out.println("Error occurred while uploading file.");
        }
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());

    }
}