package com.example.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "config-server-client", url = "config-server-client")
public interface UserRoleModuleFeign {

	@GetMapping("/get")
	public Object getUsers();
	
	

}
