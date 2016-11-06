package com.devops.service;

import com.devops.dto.UserDTO;
/**
 * 
 * @author lujxu
 *
 */
public interface UserService {

	public UserDTO login(String username,String password);
	
	public UserDTO getUser(String uid);
	
}
