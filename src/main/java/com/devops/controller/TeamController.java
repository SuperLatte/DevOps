package com.devops.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devops.dto.TeamDTO;
import com.devops.dto.UserDTO;
import com.devops.service.serviceimpl.TeamServiceImpl;

/**
 * 
 * @author lujxu
 *
 */
@Controller
@EnableAutoConfiguration
public class TeamController {
	
	@Autowired
	TeamServiceImpl teamService;
	
	/**
	 * get all teammates by manager id
	 * @param mid
	 * @return
	 */
	@RequestMapping(value="/teammates/{mid}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getTeammatesByManagerId(@PathVariable("mid") String mid){
		Map<String, Object> data = new HashMap<>();
		List<UserDTO> list=teamService.getAllUserByManagrId(mid);
		data.put("teammates",list);
		return data;
	}
	
	/**
	 * get team by manager's uid
	 * @param mid
	 * @return
	 */
	@RequestMapping(value="/teamByMid/{mid}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getTeamByManagerId(@PathVariable("mid") String mid){
		Map<String, Object> data = new HashMap<>();
		TeamDTO team=teamService.getTeamByManagerId(mid);
		data.put("team", team);
		return data;
	}
	
	/**
	 * get team by team id
	 * @param tid
	 * @return
	 */
	@RequestMapping(value="/team/{tid}",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getTeamByTid(@PathVariable("tid") String tid){
		Map<String, Object> data = new HashMap<>();
		TeamDTO team=teamService.getTeamById(tid);
		data.put("team", team);
		return data;
	}
	
}
