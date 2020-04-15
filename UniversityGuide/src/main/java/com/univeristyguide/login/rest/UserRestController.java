package com.univeristyguide.login.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univeristyguide.login.entity.User;
import com.univeristyguide.login.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	private UserService  userService;
	
	@Autowired
	public UserRestController(UserService theUserService)
	{
		userService = theUserService;
	}

	// expose "/User" and return list of Users
	@GetMapping("/user")
	public List<User> findAll()
	{
		return userService.findAll();
	}
}
