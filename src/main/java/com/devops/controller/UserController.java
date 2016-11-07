/**
 * 
 */
package com.devops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devops.dto.UserDTO;
import com.devops.service.serviceImpl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author lujxu
 *
 */
@RestController
@EnableAutoConfiguration
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping(value="/loginAction",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginAction(@RequestParam(value="username") String username, @RequestParam(value="password") String password){
		UserDTO user=new UserDTO();
		user=userService.login(username, password);
		
		Map<String, Object> data = new HashMap<>();
		data.put("url", "./myProjects");
		data.put("username", user.getName());
		return data;
	}
	
	@RequestMapping(value="/userAction/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getUser(@PathVariable("id") String id){
		Map<String, Object> data = new HashMap<String, Object>();
		UserDTO user=new UserDTO();
		user=userService.getUser(id);
		data.put("user", user);
		return data;
	}
}
