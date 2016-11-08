package com.devops.service;

import java.util.List;

import com.devops.dto.TeamDTO;
import com.devops.dto.UserDTO;

public interface TeamService {

	/**
	 * 
	 * @param mid
	 * @return
	 */
	public List<UserDTO> getAllUserByManagrId(String mid);

	/**
	 * 
	 * @param mid
	 * @return
	 */
	public TeamDTO getTeamByManagerId(String mid);

	/**
	 * 
	 * @param tid
	 * @return
	 */
	public TeamDTO getTeamById(String tid);
}
