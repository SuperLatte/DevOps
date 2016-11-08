package com.devops.service;

import com.devops.dto.UserDTO;
/**
 * 
 * @author lujxu
 *
 */
public interface UserService {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public UserDTO login(String username,String password);

	/**
	 * 
	 * @param uid
	 * @return
	 */
	public UserDTO getUser(String uid);
	
}
