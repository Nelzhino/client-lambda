package com.example.clientLambdaExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clientLambdaExample.DTO.UserDTO;
import com.example.clientLambdaExample.services.UserService;

@RestController
@RequestMapping
public class ClientLambdaController {

	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/valid")
	public String test() {
		return "hola";
	}
	
	@PostMapping("/user")
	public String getPermissionUser(@RequestBody UserDTO userDTO) throws Exception {
		return this.userService.handlerComunicateWithLambda(userDTO);
	}
	
	
}
