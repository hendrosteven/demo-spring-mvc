package com.bca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bca.dto.ErrorMessage;
import com.bca.entity.User;
import com.bca.services.UserService;

@RestController
@RequestMapping("/user/register")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public Object register(@RequestBody User user) {
		try {
			return userService.register(user);
		} catch (Exception ex) {
			ErrorMessage msg = new ErrorMessage();
			msg.getMessages().add("Error: "+ ex.getMessage());
			return msg;
		}
	}
}
