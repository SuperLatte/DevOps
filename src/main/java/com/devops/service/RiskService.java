package com.devops.service;

import java.util.List;

import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;
import com.devops.dto.RiskTracingDTO;

/**
 * @author Shifang
 *
 */
public interface RiskService {
	
	public RiskDTO getRiskById(String id);
	
	public RiskDTO add(RiskDTO risk);
	
	public RiskDTO update(RiskDTO risk);
	
	public List<RiskDTO> getRiskByTeam(String id);
	
	public RiskRecordDTO getRiskRecordById(String id);
	
	public List<RiskRecordDTO> getRiskRecordByRid(String id);
	
	public RiskRecordDTO addRiskRecord(RiskRecordDTO riskRecordDTO);
	
	public List<RiskDTO> getRiskByUser(String uid);
	
	public RiskTracingDTO addTracing(RiskTracingDTO dto);
	
	public boolean removeTracing(RiskTracingDTO dto);
	
}
