package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class UserRoleModuleFeignController {

	@Autowired
	UserRoleModuleFeign userRoleModuleFeign;

	@GetMapping("/getUsers")
	public Object getUsers() {
		return userRoleModuleFeign.getUsers();
	}

}
