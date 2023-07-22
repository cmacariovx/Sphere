package com.cm.sphere;

import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class AwsSecretManager {
    public static class DatabaseCredentials {
        private String mongoDBString;

        public String getMongoDBString() {
            return this.mongoDBString;
        }

        public void setMongoDBString(String mongoDBString) {
            this.mongoDBString = mongoDBString;
        }
    }

    public static class SecretKeys {
        private String refreshTokenSecretKey;
        private String accessTokenSecretKey;

        public String getRefreshTokenSecretKey() {
            return this.refreshTokenSecretKey;
        }

        public String getAccessTokenSecretKey() {
            return this.accessTokenSecretKey;
        }

        public void setRefreshTokenSecretKey(String secretKey) {
            this.refreshTokenSecretKey = secretKey;
        }

        public void setAccessTokenSecretKey(String secretKey) {
            this.accessTokenSecretKey = secretKey;
        }
    }

    public static DatabaseCredentials getSecretMongo() {
        String secretName = "mongoDBString";
        Region region = Region.of("us-east-1");

        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse = null;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        }
        catch (Exception e) {
            throw e;
        }

        String secretString = getSecretValueResponse.secretString();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(secretString, DatabaseCredentials.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse secret string", e);
        }
    }

    public static SecretKeys getTokenSecretKeys() {
        String secretName = "tokenSecretKeys";
        Region region = Region.of("us-east-1");

        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse = null;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        }
        catch (Exception e) {
            throw e;
        }

        String secretString = getSecretValueResponse.secretString();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(secretString, SecretKeys.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse secret string", e);
        }
    }
}
