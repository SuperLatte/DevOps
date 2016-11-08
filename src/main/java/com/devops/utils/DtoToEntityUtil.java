package com.devops.utils;

import org.springframework.util.StringUtils;

import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;
import com.devops.dto.RiskTracingDTO;
import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
import com.devops.entity.RiskTracing;

public class DtoToEntityUtil {
	
	public static Risk RiskDtoToEntity(RiskDTO dto){
		if(dto==null)
			return null;
		Risk risk=new Risk();
		risk.setRid(dto.getRid());
		risk.setName(dto.getName());
		risk.setTid(dto.getTid());
		risk.setCreateTime(dto.getCreateTime());
		risk.setUpdateTime(dto.getUpdateTime());
		risk.setStatus(dto.getStatus());
		return risk;
	}
	
	public static RiskRecord RiskRecordDtoToEntity(RiskRecordDTO dto){
		if(dto==null)
			return null;
		RiskRecord record=new RiskRecord();
		record.setRrid(dto.getRrid());
		record.setRid(dto.getRid());
		record.setTraceUserid(dto.getTraceUserId());
		record.setAffection(dto.getAffection());
		record.setContent(dto.getContent());
		record.setCreateTime(dto.getCreateTime());
		record.setPossibility(dto.getPossibility());
		record.setTrigger(dto.getTrigger());
		return record;
	}
	
	public static RiskTracing RiskTracingToEntity(RiskTracingDTO dto){

		if(dto==null||StringUtils.isEmpty(dto.getRid())||StringUtils.isEmpty(dto.getUid()))
			return null;
		RiskTracing tracing=new RiskTracing();
		tracing.setRid(dto.getRid());
		tracing.setUid(dto.getUid());
		return tracing;
	}

}
