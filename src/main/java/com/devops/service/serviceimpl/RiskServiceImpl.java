package com.devops.service.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

	Logger logger = Logger.getLogger("com.devops.service.serviceImpl.RiskServiceImpl");

	@Override
	public RiskDTO getRiskById(String id) {
		Risk risk = null;
		try {
			risk = riskDao.getRiskByRiskID(id);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		if (risk == null) {
			return null;
		}
		return EntityToDtoUtil.riskToRiskDTO(risk);
	}

	@Override
	public List<RiskDTO> getRiskByUserId(String uid) {
		List<RiskDTO> riskDTOs = new ArrayList<>();
		try {
			List<Risk> risks = riskDao.getRiskByUserID(uid);
			for (Risk risk: risks) {
				RiskDTO rd = EntityToDtoUtil.riskToRiskDTO(risk);
				riskDTOs.add(rd);
			}
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}

		return riskDTOs;
	}

	@Override
	public List<RiskDTO> getRiskByTeam(String id) {
		List<Risk> riskList = null;
		try {
			riskList = riskDao.getRiskByTeamID(id);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		if (riskList == null || riskList.isEmpty())
			return new ArrayList<>();
		List<RiskDTO> dtoList = new ArrayList<>();
		for (Risk risk : riskList) {
			RiskDTO dto = EntityToDtoUtil.riskToRiskDTO(risk);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public RiskDTO add(RiskDTO riskDTO) {

		if (riskDTO == null)
			return null;

		Risk risk = DtoToEntityUtil.riskDtoToEntity(riskDTO);
		int rid = 0;
		try {
			rid = riskDao.addRisk(risk);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		if (rid == -1) {
			return null;
		}
		String ridValue = String.valueOf(rid);
		List<RiskTracingDTO> userList = riskDTO.getTraceUserList();

		for (RiskTracingDTO tracingDTO : userList) {
			RiskTracing tracing = DtoToEntityUtil.riskTracingToEntity(tracingDTO);
			try {
				riskDao.addTracing(tracing);
			} catch (SQLException e) {
				logger.severe(e.getMessage());
				return riskDTO;
			}
		}

		return getRiskById(ridValue);

	}

	
	@Override
	public RiskDTO update(RiskDTO riskDTO) {
		if (riskDTO == null || StringUtils.isEmpty(riskDTO.getRid()))
			return null;

		String rid = riskDTO.getRid();

		Risk risk = null;
		try {
			risk = riskDao.getRiskByRiskID(rid);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		
		if (risk == null)
			return null;
		
		modifyRisk(riskDTO,risk);
		
		try {
			riskDao.editRisk(risk);
		} catch (SQLException e2) {
			logger.severe(e2.getMessage());
			return riskDTO;
		}
		return EntityToDtoUtil.riskToRiskDTO(risk);
	}
	
	private void modifyRisk(RiskDTO riskDTO,Risk risk){
		
		if (!StringUtils.isEmpty(riskDTO.getName()))
			risk.setName(riskDTO.getName());
		if (!StringUtils.isEmpty(riskDTO.getTid()))
			risk.setTid(riskDTO.getTid());
		if (riskDTO.getCreateTime() > 0)
			risk.setCreateTime(riskDTO.getCreateTime());
		if (riskDTO.getUpdateTime() > 0)
			risk.setUpdateTime(riskDTO.getUpdateTime());
	}
	
	@Override
	public RiskRecordDTO getRiskRecordById(String id) {
		RiskRecord record = null;
		try {
			record = riskDao.getRecord(id);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		RiskRecordDTO dto = EntityToDtoUtil.riskRecordToDTO(record);
		String userId = dto.getTraceUserId();
		try {
			User user = userDao.getUser(userId);
			if (user != null)
				dto.setTraceUserName(user.getName());
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		return dto;
	}

	@Override
	public RiskRecordDTO addRiskRecord(RiskRecordDTO riskRecordDTO) {
		if (riskRecordDTO == null)
			return null;

		RiskRecord record = DtoToEntityUtil.riskRecordDtoToEntity(riskRecordDTO);
		int rrid = 0;
		try {
			rrid = riskDao.addRecord(record);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		if (rrid == -1) {
			return null;
		}

		try {
			record = riskDao.getRecord(String.valueOf(rrid));
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}

		return EntityToDtoUtil.riskRecordToDTO(record);

	}

	@Override
	public List<RiskRecordDTO> getRiskRecordByRid(String id) {
		List<RiskRecord> list = null;
		try {
			list = riskDao.getRecords(id);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		List<RiskRecordDTO> result = new ArrayList<>();
		for (RiskRecord record : list) {
			RiskRecordDTO dto = EntityToDtoUtil.riskRecordToDTO(record);
			String uid = dto.getTraceUserId();
			if (uid != null) {
				try {
					String name = userDao.getUser(uid).getName();
					dto.setTraceUserName(name);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			result.add(dto);
		}

		return result;
	}

	@Override
	public List<RiskDTO> getRiskByUser(String uid) {
		List<Risk> list = new ArrayList<>();
		try {
			list = riskDao.getRiskByUserID(uid);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}

		List<RiskDTO> result = new ArrayList<>();
		try {
			for (Risk risk : list) {
				RiskDTO dto = EntityToDtoUtil.riskToRiskDTO(risk);

				 List<RiskTracing> tracingList = riskDao.getTracingByRiskID(risk.getRid());

				for (RiskTracing tracing : tracingList) {
					RiskTracingDTO tracingDTO = EntityToDtoUtil.riskTracingToDTO(tracing);
					modifyRiskTracingDTO(tracingDTO);
					dto.addTracingUser(tracingDTO);
				}
				result.add(dto);
			}
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		return result;
	}

	private  void modifyRiskTracingDTO(RiskTracingDTO tracingDTO) throws SQLException{
		User user = userDao.getUser(tracingDTO.getUid());
		if (user != null) {
			tracingDTO.setName(user.getName());
			tracingDTO.setUsername(user.getUsername());
		}
	}
	
	@Override
	public RiskTracingDTO addTracing(RiskTracingDTO dto) {

		RiskTracing tracing = DtoToEntityUtil.riskTracingToEntity(dto);
		boolean success = false;
		try {
			success = riskDao.addTracing(tracing);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		RiskTracingDTO returnDTO = null;
		if (success) {
			returnDTO = new RiskTracingDTO();
			returnDTO.setRid(dto.getRid());
			returnDTO.setUid(dto.getUid());
			try {
				User user = userDao.getUser(dto.getUid());
				if (user != null) {
					returnDTO.setName(user.getName());
					returnDTO.setUsername(user.getUsername());
				}
			} catch (SQLException e) {
				logger.severe(e.getMessage());
			}
		}

		return returnDTO;
	}

	@Override
	public boolean removeTracing(RiskTracingDTO dto) {
		RiskTracing tracing = DtoToEntityUtil.riskTracingToEntity(dto);
		boolean success = false;
		try {
			success = riskDao.addTracing(tracing);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		return success;
	}

}
