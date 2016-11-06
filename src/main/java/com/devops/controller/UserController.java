/**
 * 
 */
package com.devops.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devops.dto.UserDTO;

@RestController
@EnableAutoConfiguration
public class UserController {

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody UserDTO login(@RequestParam(value="username") String username,@RequestParam(value="password") String password){
		UserDTO user=new UserDTO();
		user.setUsername(username);
		user.setUid("123");
		return user;
	}
	
	
}
