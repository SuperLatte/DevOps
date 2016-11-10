/**
 * 
 */
package com.devops.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devops.dto.RiskDTO;
import com.devops.dto.UserDTO;
import com.devops.service.RiskService;
import com.devops.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//import javax.servlet.http.HttpServletRequest;

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
			data.put("loginResponse", "SUCCESS");
			List<RiskDTO> riskDTOList = riskService.getRiskByUser(user.getUid());
			data.put("riskList", JSONArray.fromObject(riskDTOList).toString());
			data.put("user", JSONObject.fromObject(user).toString());
			data.put("url", "./myProjects");
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
