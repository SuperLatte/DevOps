package com.devops.utils;

import org.springframework.util.StringUtils;

import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;
import com.devops.dto.UserDTO;
import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
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
	
	public static RiskDTO RiskToRiskDTO(Risk risk){
		if(risk==null)
			return null;
		RiskDTO dto=new RiskDTO();
		dto.setRid(risk.getRid());
		dto.setName(risk.getName());
		dto.setTid(risk.getTid());
		dto.setCreateTime(risk.getCreateTime());
		dto.setUpdateTime(risk.getUpdateTime());
		
		return dto;
	}
	
	public static RiskRecordDTO RiskRecordToDTO(RiskRecord riskRecord){
		if(riskRecord==null)
			return null;
		RiskRecordDTO dto=new RiskRecordDTO();
		dto.setRid(riskRecord.getRid());
		dto.setTraceUserId(riskRecord.getRrid());
		//TODO
		return dto;
		
		
	}
}
