package com.automation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient;

@RestController
public class Welcome {

	@Autowired
	private AmazonSQSAsync amazonSqsClient;

	@SuppressWarnings("deprecation")
	@Bean
	public AmazonSQSAsync amazonSQS() {
		AmazonSQSAsyncClient amazonSQSAsyncClient = new AmazonSQSAsyncClient(new DefaultAWSCredentialsProviderChain());

		amazonSQSAsyncClient.setRegion(com.amazonaws.regions.Region.getRegion(Regions.US_EAST_1));
		return new AmazonSQSBufferedAsyncClient(amazonSQSAsyncClient);
	}

	@RequestMapping("/index")
	public String welcome() {
		return "Welcome to the EB stack automation";
	}

	@SqsListener(value="ss-test",deletionPolicy=SqsMessageDeletionPolicy.NEVER)
	@RequestMapping("/msg")
	public void onMessage(String message) {
		System.out.println(message);
	}

}