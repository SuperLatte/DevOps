package com.devops.service.serviceimpl;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.dao.impl.UserDaoImpl;
import com.devops.dto.UserDTO;
import com.devops.entity.User;
import com.devops.service.UserService;
import com.devops.utils.EntityToDtoUtil;

/**
 * 
 * @author lujxu
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDaoImpl userDao;
	
	Logger logger=Logger.getLogger("com.devops.service.serviceImpl.UserServiceImpl");
	
	@Override
	public UserDTO login(String username, String password){
		User u=new User();
		try {
			u=userDao.getUser(username, password);
			logger.info("login: username:"+username);
		} catch (SQLException e) {
			u=null;
			logger.severe(e.getMessage());
		}
		if(u==null) 
			new UserDTO();
		return EntityToDtoUtil.userToUserDTO(u);
	}

	@Override
	public UserDTO getUser(String uid) {
		User u=new User();
		try {
			u=userDao.getUser(uid);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		if(u==null) 
			new UserDTO();
		return EntityToDtoUtil.userToUserDTO(u);
	}

}
