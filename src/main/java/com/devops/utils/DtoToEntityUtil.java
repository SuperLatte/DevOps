package com.devops.utils;

import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;
import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;

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

}
