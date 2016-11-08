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
import com.devops.dto.RiskTracingDTO;
import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
import com.devops.entity.RiskTracing;
import com.devops.entity.User;
import com.devops.service.RiskService;
import com.devops.utils.DtoToEntityUtil;
import com.devops.utils.EntityToDtoUtil;

/**
 * @author Shifang
 *
 */
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
	public RiskDTO add(RiskDTO riskDTO) {
		
		if(riskDTO==null)
			return null;
		
		Risk risk=DtoToEntityUtil.RiskDtoToEntity(riskDTO);
		int rid=0;
		try {
			rid=riskDao.addRisk(risk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rid==-1){
			return null;
		}
		String ridValue=String.valueOf(rid);
		List<RiskTracingDTO> userList=riskDTO.getTraceUserList();

		for(RiskTracingDTO tracingDTO:userList){
			RiskTracing tracing=DtoToEntityUtil.RiskTracingToEntity(tracingDTO);
			try {
				riskDao.addTracing(tracing);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Risk returnRisk=null;
		try {
			returnRisk=riskDao.getRiskByRiskID(ridValue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RiskDTO returnDTO=EntityToDtoUtil.RiskToRiskDTO(returnRisk);
		try {
			List<RiskTracing> tracingList = riskDao.getTracingByRiskID(String.valueOf(rid));
			for(RiskTracing tracing:tracingList){
				RiskTracingDTO dto=EntityToDtoUtil.RiskTracingToDTO(tracing);
				returnDTO.addTracingUser(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnDTO;
		
	}

	@Override
	public RiskDTO update(RiskDTO riskDTO) {
		if(riskDTO==null||StringUtils.isEmpty(riskDTO.getRid()))
			return null;
		
		String rid=riskDTO.getRid();
		
		Risk risk=null;
		try {
			risk=riskDao.getRiskByRiskID(rid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(risk==null)
			return null;
		if(!StringUtils.isEmpty(riskDTO.getName()))
			risk.setName(riskDTO.getName());
		if(!StringUtils.isEmpty(riskDTO.getTid()))
			risk.setTid(riskDTO.getTid());
		if(riskDTO.getCreateTime()>0)
			risk.setCreateTime(riskDTO.getCreateTime());
		if(riskDTO.getUpdateTime()>0)
			risk.setUpdateTime(riskDTO.getUpdateTime());
		
		try {
			riskDao.editRisk(risk);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		try {
			risk=riskDao.getRiskByRiskID(rid);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		RiskDTO updatedDTO=EntityToDtoUtil.RiskToRiskDTO(risk);
		List<RiskTracing> updatedRiskTracingList=null;
		try {
			updatedRiskTracingList = riskDao.getTracingByRiskID(rid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(RiskTracing tracing:updatedRiskTracingList){
			RiskTracingDTO dto=EntityToDtoUtil.RiskTracingToDTO(tracing);
			
			try {
				User user=userDao.getUser(dto.getUid());
				if(user!=null){
					dto.setName(user.getName());
					dto.setUsername(user.getUsername());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			updatedDTO.addTracingUser(dto);
		}
		
		
		return updatedDTO;
		
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
	public RiskRecordDTO addRiskRecord(RiskRecordDTO riskRecordDTO) {
		if(riskRecordDTO==null)
			return null;
			
		RiskRecord record=DtoToEntityUtil.RiskRecordDtoToEntity(riskRecordDTO);
		int rrid=0;
		try {
			rrid=riskDao.addRecord(record);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rrid==-1){
			return null;
		}
		
		try {
			record = riskDao.getRecord(String.valueOf(rrid));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RiskRecordDTO updatedDTO=EntityToDtoUtil.RiskRecordToDTO(record);
		
		return updatedDTO;
		
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
		List<Risk> list = new ArrayList<Risk>();
		try{
			list=riskDao.getRiskByUserID(uid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<RiskDTO> result=new ArrayList<RiskDTO>();
		for(Risk risk:list){
			RiskDTO dto=EntityToDtoUtil.RiskToRiskDTO(risk);
			List<RiskTracing> tracingList=null;
			try {
				tracingList = riskDao.getTracingByRiskID(risk.getRid());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for(RiskTracing tracing:tracingList){
				RiskTracingDTO tracingDTO=EntityToDtoUtil.RiskTracingToDTO(tracing);
				try {
					User user=userDao.getUser(tracingDTO.getUid());
					if(user!=null){
						tracingDTO.setName(user.getName());
						tracingDTO.setUsername(user.getUsername());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				dto.addTracingUser(tracingDTO);
			}
			result.add(dto);
		}
		
		return result;
	}

	@Override
	public RiskTracingDTO addTracing(RiskTracingDTO dto) {
		
		RiskTracing tracing=DtoToEntityUtil.RiskTracingToEntity(dto);
		boolean success=false;
		try {
			success=riskDao.addTracing(tracing);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RiskTracingDTO returnDTO=null;
		if(success){
			returnDTO=new RiskTracingDTO();
			returnDTO.setRid(dto.getRid());
			returnDTO.setUid(dto.getUid());
			try {
				User user=userDao.getUser(dto.getUid());
				if(user!=null){
					returnDTO.setName(user.getName());
					returnDTO.setUsername(user.getUsername());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return returnDTO;
	}

	@Override
	public boolean removeTracing(RiskTracingDTO dto) {
		RiskTracing tracing=DtoToEntityUtil.RiskTracingToEntity(dto);
		boolean success=false;
		try {
			success=riskDao.addTracing(tracing);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	

	

}
