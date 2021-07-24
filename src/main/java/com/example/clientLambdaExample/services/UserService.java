package com.example.clientLambdaExample.services;

import com.example.clientLambdaExample.DTO.UserDTO;

public interface UserService {

	
	String handlerComunicateWithLambda(UserDTO userDTO) throws Exception;
	
	
}
