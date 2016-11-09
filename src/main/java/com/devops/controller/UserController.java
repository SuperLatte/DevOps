/**
 * 
 */
package com.devops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devops.dto.UserDTO;
import com.devops.service.serviceimpl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	@Autowired
	HttpServletRequest request;

	/**
	 * user login
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginAction(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		UserDTO user = userService.login(username, password);
		Map<String, Object> data = new HashMap<>();
		data.put("url", "./myProjects");
		if (user != null && !StringUtils.isEmpty(user.getName()))
			data.put("username", user.getName());
		else
			data.put("username", null);
		request.getSession().setAttribute("user", user);
		return data;
	}

	/**
	 * get user by userId
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/userAction/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUser(@PathVariable("id") String id) {
		Map<String, Object> data = new HashMap<>();
		UserDTO user = userService.getUser(id);
		data.put("user", user);
		return data;
	}
}
