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
	/**
	 * 
	 * @param id
	 * @return
	 */
	public RiskDTO getRiskById(String id);

	public List<RiskDTO> getRiskByUserId(String uid);


	/**
	 * 
	 * @param risk
	 * @return
	 */
	public RiskDTO add(RiskDTO risk);
	
	/**
	 * 
	 * @param risk
	 * @return
	 */
	public RiskDTO update(RiskDTO risk);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<RiskDTO> getRiskByTeam(String id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public RiskRecordDTO getRiskRecordById(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<RiskRecordDTO> getRiskRecordByRid(String id);

	/**
	 * 
	 * @param riskRecordDTO
	 * @return
	 */
	public RiskRecordDTO addRiskRecord(RiskRecordDTO riskRecordDTO);

	/**
	 * 
	 * @param uid
	 * @return
	 */
	public List<RiskDTO> getRiskByUser(String uid);

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public RiskTracingDTO addTracing(RiskTracingDTO dto);

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public boolean removeTracing(RiskTracingDTO dto);
	
}
