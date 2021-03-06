/**
 * 
 */
package com.devops.controller;

import java.util.HashMap;
import java.util.Map;

import com.devops.service.TeamService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
import com.devops.service.RiskService;
import com.devops.service.UserService;

import net.sf.json.JSONObject;

/**
 * 
 * @author lujxu
 *
 */
@RestController
@EnableAutoConfiguration
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RiskService riskService;
	@Autowired
	private TeamService teamService;

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

		if (user != null && !StringUtils.isEmpty(user.getName())){
			request.getSession().setAttribute("user", user);

			String tid = "0";

			if (user.getLevel() == 1) {
				tid = teamService.getTeamByManagerId(user.getUid()).getTid();
			}
			request.getSession().setAttribute("tid", tid);

			data.put("loginResponse", "SUCCESS");
			data.put("url", "./myProjects");
			data.put("user", JSONObject.fromObject(user).toString());
		} else {
			data.put("loginResponse", "FAILED");
		}
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
