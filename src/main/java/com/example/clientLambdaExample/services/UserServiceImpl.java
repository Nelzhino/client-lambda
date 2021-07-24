package com.example.clientLambdaExample.services;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.example.clientLambdaExample.DTO.UserDTO;
import com.example.clientLambdaExample.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Value("${name}")
	private String nameFunction;
	
	@Value("${accessKeyId}")
	private String accessKeyId;
	
	@Value("${secretKeyId}")
	private String secretKeyId;
	
	
	@Override
	public String handlerComunicateWithLambda(UserDTO userDTO) throws Exception {
		// (1) Define the AWS Region in which the function is to be invoked and add Basic credentials with user grant Lambdas 
		// (2) Instantiate AWSLambdaClientBuilder to build the Lambda client
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretKeyId);
		AWSLambdaClientBuilder builder = AWSLambdaClientBuilder.standard()
		                                    .withRegion(Regions.US_EAST_1)
		                                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds));
		// (3) Build the client, which will ultimately invoke the function
		AWSLambda client = builder.build();
		// (4) Create an InvokeRequest with required parameters
		InvokeRequest req = new InvokeRequest()
		                           .withFunctionName(nameFunction)
		                           .withPayload(Utils.converObjectToJson(userDTO)); 
		// (5) Invoke the function and capture response
		InvokeResult invokeResult = client.invoke(req);
		
		
		// (6) Valid payload and get result
        String payload = "";
        if(invokeResult.getPayload() != null){
            payload = new String(invokeResult.getPayload().array(), Charset.forName("UTF-8"));
        }
        
        
        return payload;
	}

	
	
}
