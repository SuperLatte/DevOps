package com.devops.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;
import com.devops.service.RiskService;

@RestController
@EnableAutoConfiguration
public class RiskController {
	
	@Autowired
	RiskService service;
	
	@RequestMapping("/risk")
	@ResponseBody
	public List<RiskDTO> list(@RequestParam(value = "tid") String tid){
		System.out.println("tid="+tid);
		List<RiskDTO> list=service.getRiskByTeam(tid);
		RiskDTO dto=new RiskDTO();
		dto.setName("test name");
		list=new ArrayList<RiskDTO>();
		list.add(dto);
		
		
		return list;
		
	}
	
	@RequestMapping(value="/risk/create",method=RequestMethod.POST)
	@ResponseBody
	public String create( @RequestBody RiskDTO risk){
		System.out.println(risk);
		risk.setName("testRiskName");
		risk.setTid("1");
		if(StringUtils.isEmpty(risk.getName())||StringUtils.isEmpty(risk.getTid()))
			return "not enough information";
		service.add(risk);
		return "success";
	}
	
	@RequestMapping("/risk/{id}")
	@ResponseBody
	public RiskDTO view(@PathVariable(value="id") String id) {  
        System.out.println("rid="+id);
		RiskDTO risk=service.getRiskById(id);
	
        return risk;  
    }
	
	@RequestMapping("/risk/{id}/detail")
	@ResponseBody
	public List<RiskRecordDTO> detail(@PathVariable(value="id")String id){
		 System.out.println("id="+id);
		List<RiskRecordDTO> list=service.getRiskRecordByRid(id);
		return list;
	}
	
	
	


}
