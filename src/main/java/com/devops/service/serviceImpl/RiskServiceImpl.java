package com.devops.service.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.dao.RiskDao;
import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;
import com.devops.entity.Risk;
import com.devops.service.RiskService;

@Service
public class RiskServiceImpl implements RiskService {
	
	@Autowired
	RiskDao dao;

	@Override
	public RiskDTO getRiskById(String id) {
		Risk risk=null;
		try {
			risk = dao.getRiskByRiskID(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(risk==null){
			return null;
		}
		else{
			//TODO
		}
		return new RiskDTO();
	}

	@Override
	public List<RiskDTO> getRiskByTeam(String id) {
		//TODO
//		List<Risk> riskList=dao.getRiskByTeamID(id);
		return null;
	}
	
	@Override
	public void add(RiskDTO riskDTO) {
		Risk risk=new Risk();
		try {
			dao.addRisk(risk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//TODO
	}

	@Override
	public void update(RiskDTO risk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RiskRecordDTO getRiskRecordById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRiskRecord(RiskRecordDTO riskRecordDTO) {
		
		
	}

	

}
