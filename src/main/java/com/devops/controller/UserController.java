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

import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class UserController {

	@RequestMapping(value="/loginAction",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginAction(@RequestParam(value="username") String username, @RequestParam(value="password") String password){
//		UserDTO user=new UserDTO();
//		user.setUsername(username);
//		user.setUid("123");
//		return user;
		System.out.println(username + "\t" + password);

		Map<String, Object> data = new HashMap<>();
		data.put("url", '/');
		return data;
	}
	
	
}
