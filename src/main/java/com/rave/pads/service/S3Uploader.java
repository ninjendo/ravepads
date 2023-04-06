package com.rave.pads.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class S3Uploader implements RequestHandler<Map<String,Object>, String> {

    private static final String BUCKET_NAME = "ravepads-uploaded-files-bucket";
    private static final String TABLE_NAME = "uploaded-files-db";

    private final AmazonS3 s3Client;
    private final DynamoDB dynamoDB;

    public S3Uploader() {
        s3Client = AmazonS3ClientBuilder.defaultClient();
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();
        dynamoDB = new DynamoDB(dynamoDBClient);
    }

    public String handleRequest(Map<String,Object> input, Context context) {
        String fileName = (String) input.get("fileName");
        String contentType = (String) input.get("contentType");

        // Generate a presigned URL for the file upload
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(BUCKET_NAME, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withContentType(contentType);
        String presignedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();

        // Store the file reference in DynamoDB
        String objectKey = fileName + System.currentTimeMillis();
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("objectKey", new AttributeValue(objectKey));
        item.put("bucketName", new AttributeValue(BUCKET_NAME));
        item.put("contentType", new AttributeValue(contentType));
        Table table = dynamoDB.getTable(TABLE_NAME);
        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName(TABLE_NAME)
                .withItem(item);
        table.putItem(new Item()
                .withPrimaryKey("objectKey", objectKey)
                .withString("bucketName", BUCKET_NAME)
                .withString("contentType", contentType));

        return presignedUrl;
    }
}
