package com.rabbitmqudemy.MS_Notificacao.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonSnsConfiguration {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.privateKey}")
    private String privateKey;

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(accessKey, privateKey);
    }

    @Bean
    public AmazonSNS amazonSNS() {
        return AmazonSNSClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(awsCredentials()))
                .withRegion(Regions.US_EAST_1).build();
    }
}
