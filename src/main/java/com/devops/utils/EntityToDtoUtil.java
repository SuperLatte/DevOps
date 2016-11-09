package com.devops.utils;

import org.springframework.util.StringUtils;

import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;
import com.devops.dto.RiskTracingDTO;
import com.devops.dto.TeamDTO;
import com.devops.dto.UserDTO;
import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
import com.devops.entity.RiskTracing;
import com.devops.entity.Team;
import com.devops.entity.User;
/**
 * 
 * @author lujxu
 *
 */
public class EntityToDtoUtil {

	private EntityToDtoUtil(){
		
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public static UserDTO userToUserDTO(User user){
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
	
	/**
	 * 
	 * @param risk
	 * @return
	 */
	public static RiskDTO riskToRiskDTO(Risk risk){
		if(risk==null)
			return null;
		RiskDTO dto=new RiskDTO();
		dto.setRid(risk.getRid());
		dto.setName(risk.getName());
		dto.setTid(risk.getTid());
		dto.setCreateTime(risk.getCreateTime());
		dto.setUpdateTime(risk.getUpdateTime());
		dto.setStatus(risk.getStatus());
		return dto;
	}
	
	/**
	 * 
	 * @param riskRecord
	 * @return
	 */
	public static RiskRecordDTO riskRecordToDTO(RiskRecord riskRecord){
		if(riskRecord==null)
			return null;
		RiskRecordDTO dto=new RiskRecordDTO();
		dto.setRid(riskRecord.getRid());
		dto.setTraceUserId(riskRecord.getRrid());
		return dto;
	}	
		
	/**
	 * 
	 * @param team
	 * @return
	 */
	public static TeamDTO teamToTeamDTO(Team team){
		if(team==null) 
			return null;
		TeamDTO teamDTO=new TeamDTO();
		if(!StringUtils.isEmpty(team.getManagerId()))
			teamDTO.setManagerId(team.getManagerId());
		if(!StringUtils.isEmpty(team.getName()))
			teamDTO.setName(team.getName());
		if(!StringUtils.isEmpty(team.getTid()))
			teamDTO.setTid(team.getTid());
		return teamDTO;
	}
	
	/**
	 * 
	 * @param tracing
	 * @return
	 */
	public static RiskTracingDTO riskTracingToDTO(RiskTracing tracing){
		if(tracing==null)
			return null;
		RiskTracingDTO dto=new RiskTracingDTO();
		dto.setRid(tracing.getRid());
		dto.setUid(tracing.getUid());
		
		return dto;
		
	}
}
