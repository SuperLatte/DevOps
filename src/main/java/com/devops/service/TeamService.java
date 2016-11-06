package com.devops.service;

import java.util.List;

import com.devops.dto.TeamDTO;
import com.devops.dto.UserDTO;

public interface TeamService {

	public List<UserDTO> getAllUserByManagrId(String mid);
	
	public TeamDTO getTeamByManagerId(String mid);
	
	public TeamDTO getTeamById(String tid);
}
