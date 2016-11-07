package com.devops.service.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devops.dao.RiskDao;
import com.devops.dao.UserDao;
import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;
import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
import com.devops.entity.RiskTracing;
import com.devops.entity.User;
import com.devops.service.RiskService;
import com.devops.utils.DtoToEntityUtil;
import com.devops.utils.EntityToDtoUtil;

@Service
public class RiskServiceImpl implements RiskService {
	
	@Autowired
	RiskDao riskDao;
	@Autowired
	UserDao userDao;

	@Override
	public RiskDTO getRiskById(String id) {
		Risk risk=null;
		try {
			risk = riskDao.getRiskByRiskID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(risk==null){
			return null;
		}
		RiskDTO dto=EntityToDtoUtil.RiskToRiskDTO(risk);
		return dto;
	}

	@Override
	public List<RiskDTO> getRiskByTeam(String id) {
		List<Risk> riskList=null;
		try {
			riskList=riskDao.getRiskByTeamID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(riskList==null||riskList.isEmpty())
			return null;
		List<RiskDTO> dtoList=new ArrayList<RiskDTO>();
		for(Risk risk:riskList){
			RiskDTO dto=EntityToDtoUtil.RiskToRiskDTO(risk);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@Override
	public void add(RiskDTO riskDTO) {
		
		if(riskDTO==null)
			return;
		
		Risk risk=DtoToEntityUtil.RiskDtoToEntity(riskDTO);
		int rid=0;
		try {
			rid=riskDao.addRisk(risk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rid==-1){
			return;
		}
		String ridValue=String.valueOf(rid);
		List<String> usernameList=riskDTO.getTraceUserList();
		if(usernameList==null)
			return;
		for(String username:usernameList){
			RiskTracing tracing=new RiskTracing();
			tracing.setRid(ridValue);
			tracing.setUid(username);
			try {
				riskDao.addTracing(tracing);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(RiskDTO riskDTO) {
		if(riskDTO==null||StringUtils.isEmpty(riskDTO.getRid()))
			return;
		
		Risk risk=null;
		try {
			risk=riskDao.getRiskByRiskID(risk.getRid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(risk==null)
			return;
		if(!StringUtils.isEmpty(riskDTO.getName()))
			risk.setName(riskDTO.getName());
		if(!StringUtils.isEmpty(riskDTO.getTid()))
			risk.setTid(riskDTO.getTid());
		if(riskDTO.getCreateTime()>0)
			risk.setCreateTime(riskDTO.getCreateTime());
		if(riskDTO.getUpdateTime()>0)
			risk.setUpdateTime(riskDTO.getUpdateTime());
		
		//TODO
		
		
	}

	@Override
	public RiskRecordDTO getRiskRecordById(String id) {
		RiskRecord record=null;
		try {
			record=riskDao.getRecord(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RiskRecordDTO dto=EntityToDtoUtil.RiskRecordToDTO(record);
		String userId=dto.getTraceUserId();
		try {
			User user=userDao.getUser(userId);
			if(user!=null)
				dto.setTraceUserName(user.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void addRiskRecord(RiskRecordDTO riskRecordDTO) {
		if(riskRecordDTO==null)
			return;
			
		RiskRecord record=DtoToEntityUtil.RiskRecordDtoToEntity(riskRecordDTO);
		
		try {
			riskDao.addRecord(record);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<RiskRecordDTO> getRiskRecordByRid(String id) {
		List<RiskRecord> list=null;
		try {
			list=riskDao.getRecords(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<RiskRecordDTO> result=new ArrayList<RiskRecordDTO>();
		for(RiskRecord record:list){
			RiskRecordDTO dto=EntityToDtoUtil.RiskRecordToDTO(record);
			result.add(dto);
		}
		
		return result;
	}

	@Override
	public List<RiskDTO> getRiskByUser(String uid) {
		List<Risk> list = null;
		try{
			list=riskDao.getRiskByUserID(uid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		//TODO
		List<RiskDTO> result=new ArrayList<RiskDTO>();
		for(Risk risk:list){
			RiskDTO dto=EntityToDtoUtil.RiskToRiskDTO(risk);
			result.add(dto);
		}
		
		return result;
	}

	

}
