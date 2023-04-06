package com.rave.pads;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller("/ai-trainor")
public class UploaderController {
    private static final String BUCKET_NAME = "ravepads-uploaded-files-bucket";
    private static final String TABLE_NAME = "uploaded-files-db";

    private final AmazonS3 s3Client;
    private final DynamoDB dynamoDB;

    UploaderController(){
        s3Client = AmazonS3ClientBuilder.defaultClient();
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();
        dynamoDB = new DynamoDB(dynamoDBClient);
    }

    @Get
    public Map<String, Object> index() {
        return Collections.singletonMap("message", "Welcome to AI Trainor.");
    }

    @Get("/request-upload/{contentType}/{fileName}")
    public Map<String, Object> requestUpload(String contentType, String fileName) {

        //String fileName = (String) input.get("fileName");
        //String contentType = (String) input.get("contentType");

        // Generate a presigned URL for the file upload
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(BUCKET_NAME, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withContentType(contentType);
        String presignedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
        System.out.println("==========presignedUrl: "+presignedUrl);
        System.out.println("==========contentType: "+contentType);
        System.out.println("==========fileName: "+fileName);
        // Store the file reference in DynamoDB
        String objectkey = fileName + System.currentTimeMillis();
        System.out.println("==========objectKey: "+objectkey);
        Table table = dynamoDB.getTable(TABLE_NAME);
        table.putItem(new Item()
                .withPrimaryKey("objectkey", objectkey)
                .withString("bucketName", BUCKET_NAME)
                .withString("contentType", contentType));
        return Collections.singletonMap("message", "presignedUrl: " + presignedUrl);
    }

    @Get("/pre-process-data/{fileName}")
    public Map<String, Object> preProcessData(String fileName) {
        return Collections.singletonMap("message", "Pre-processing the file: " + fileName + "...");
    }
}
