package com.devops.service;

import java.util.List;

import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;


public interface RiskService {
	
	public RiskDTO getRiskById(String id);
	
	public void add(RiskDTO risk);
	
	public void update(RiskDTO risk);
	
	public List<RiskDTO> getRiskByTeam(String id);
	
	public RiskRecordDTO getRiskRecordById(String id);
	
	public List<RiskRecordDTO> getRiskRecordByRid(String id);
	
	public void addRiskRecord(RiskRecordDTO riskRecordDTO);

	
	
}
