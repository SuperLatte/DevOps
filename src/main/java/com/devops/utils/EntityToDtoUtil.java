package com.devops.utils;

import org.springframework.util.StringUtils;

import com.devops.dto.UserDTO;
import com.devops.entity.User;
/**
 * 
 * @author lujxu
 *
 */
public class EntityToDtoUtil {

	public static UserDTO UserToUserDTO(User user){
		if(user==null)
			return null;
		UserDTO userDTO=new UserDTO();
		if(!StringUtils.isEmpty(user.getName()))
			userDTO.setName(user.getName());
		if(!StringUtils.isEmpty(user.getUid()) )
			userDTO.setUid(user.getUid());
		if(!StringUtils.isEmpty(user.getUsername()))
			userDTO.setUsername(user.getUsername());
		return userDTO;
	}
}
