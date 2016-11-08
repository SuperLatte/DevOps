package com.devops.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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

import com.devops.dto.ResponseMessage;
import com.devops.dto.RiskDTO;
import com.devops.dto.RiskRecordDTO;
import com.devops.dto.UserDTO;
import com.devops.service.RiskService;

@RestController
@EnableAutoConfiguration
public class RiskController {
	
	@Autowired
	RiskService service;
	@Autowired
	HttpServletRequest  request;
	
	@RequestMapping("/risk")
	@ResponseBody
	public ResponseMessage<List<RiskDTO>> list(@RequestParam(value = "tid") String tid){
		
		ResponseMessage<List<RiskDTO>> response=new ResponseMessage<>();
		
		List<RiskDTO> list=service.getRiskByTeam(tid);
		if(list!=null&&!list.isEmpty()){
			response.setSuccess(true);
			response.setMessage("success");
			response.setData(list);
		}else{
			response.setSuccess(false);
			response.setMessage("There is no risk in this team.");
		}
		
		
		return response;
		
	}
	
	@RequestMapping(value="/risk/create",method=RequestMethod.POST)
	@ResponseBody
	public ResponseMessage<RiskDTO> create( @RequestBody RiskDTO risk){
		
		ResponseMessage<RiskDTO> response=new ResponseMessage<>();
		if(StringUtils.isEmpty(risk.getName())){
			response.setSuccess(false);
			response.setMessage("Please enter risk name");
		}
		if(StringUtils.isEmpty(risk.getTid())){
			response.setSuccess(false);
			response.setMessage("Please enter team");
		}
			
		RiskDTO returnDTO=service.add(risk);
		response.setSuccess(true);
		response.setMessage("success");
		response.setData(returnDTO);
		
		return response;
		
	}
	
	@RequestMapping("/risk/{id}")
	@ResponseBody
	public ResponseMessage<RiskDTO> view(@PathVariable(value="id") String id) {  
		ResponseMessage<RiskDTO> response=new ResponseMessage<>();
		RiskDTO risk=service.getRiskById(id);
		if(risk!=null){
			response.setSuccess(true);
			response.setMessage("success");
			response.setData(risk);
		}else{
			response.setSuccess(false);
			response.setMessage("The Risk ID is invalid");
		}
        return response;  
    }
	
	@RequestMapping("/risk/{id}/detail")
	@ResponseBody
	public ResponseMessage<List<RiskRecordDTO>> detail(@PathVariable(value="id")String id){
		ResponseMessage<List<RiskRecordDTO>> response=new ResponseMessage<>();
		List<RiskRecordDTO> list=service.getRiskRecordByRid(id);
		if(list!=null&&!list.isEmpty()){
			response.setSuccess(true);
			response.setMessage("success");
			response.setData(list);
		}else{
			response.setSuccess(false);
			response.setMessage("There is no risk in this team.");
		}
		return response;
	}
	
	@RequestMapping("/myrisk")
	@ResponseBody
	public ResponseMessage<List<RiskDTO>> myRisk(){
		
		ResponseMessage<List<RiskDTO>> response=new ResponseMessage<>();
		
		UserDTO user=(UserDTO)request.getSession().getAttribute("user");
		
		if(user==null){
			response.setSuccess(false);
			response.setMessage("Please Login");
		}else{
			List<RiskDTO> list=service.getRiskByUser(user.getUid());
			if(list==null||!list.isEmpty()){
				response.setSuccess(false);
				response.setMessage("You are not assigned to any risk");
			}else{
				response.setSuccess(true);
				response.setMessage("success");
				response.setData(list);
			}
		}

		return response;
		
	}
	


}
