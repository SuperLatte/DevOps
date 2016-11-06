package com.devops.service.serviceImpl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.dao.UserDao;
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
	
	@Override
	public UserDTO login(String username, String password){
		User u=new User();
		try {
			u=userDao.getUser(username, password);
			userDao.getAllUser();
		} catch (SQLException e) {
			u=null;
			e.printStackTrace();
		}
		if(u==null) return null;
		return EntityToDtoUtil.UserToUserDTO(u);
	}

}
