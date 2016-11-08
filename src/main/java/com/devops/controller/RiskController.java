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
import com.devops.dto.RiskTracingDTO;
import com.devops.dto.UserDTO;
import com.devops.service.RiskService;

/**
 * @author Shifang
 *
 */
@RestController
@EnableAutoConfiguration
public class RiskController {
	
	@Autowired
	RiskService service;
	@Autowired
	HttpServletRequest  request;
	
	/**
	 * 
	 * @param tid
	 * @return
	 */
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
	
	/**
	 * 
	 * @param risk
	 * @return
	 */
	@RequestMapping(value="/risk/create",method=RequestMethod.POST)
	@ResponseBody
	public ResponseMessage<RiskDTO> create( @RequestBody RiskDTO risk){
		
		ResponseMessage<RiskDTO> response=new ResponseMessage<>();
		if(risk==null){
			response.setSuccess(false);
			response.setMessage("Add Risk Failure");
			response.setData(risk);
			return response;
		}
		if(StringUtils.isEmpty(risk.getName())){
			response.setSuccess(false);
			response.setMessage("Please enter risk name");
			response.setData(risk);
			return response;
		}
		if(StringUtils.isEmpty(risk.getTid())){
			response.setSuccess(false);
			response.setMessage("Please enter team");
			response.setData(risk);
			return response;
		}
		RiskDTO returnDTO=service.add(risk);
		if(returnDTO!=null){
			response.setSuccess(true);
			response.setMessage("success");
			response.setData(returnDTO);	
			return response;
		}else{
			response.setSuccess(false);
			response.setMessage("Add Risk Failure");
			response.setData(risk);	
		}
		return response;
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 
	 * @return
	 */
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
	
	/**
	 * 
	 * @param riskRecord
	 * @return
	 */
	@RequestMapping("/risk/record/create")
	@ResponseBody
	public ResponseMessage<RiskRecordDTO> createRiskRecord(@RequestBody RiskRecordDTO riskRecord){
		ResponseMessage<RiskRecordDTO> response=new ResponseMessage<>();
		if(riskRecord==null){
			response.setSuccess(false);
			response.setMessage("Add Risk Record Failure");
			response.setData(riskRecord);
			return response;
		}
		if(StringUtils.isEmpty(riskRecord.getContent())){
			response.setSuccess(false);
			response.setMessage("Please enter content");
			response.setData(riskRecord);
			return response;
		}
		if(StringUtils.isEmpty(riskRecord.getTrigger())){
			response.setSuccess(false);
			response.setMessage("Please enter trigger");
			response.setData(riskRecord);
			return response;
		}
		RiskRecordDTO dto=service.addRiskRecord(riskRecord);
		if(dto!=null){
			response.setData(dto);
			response.setSuccess(true);
			response.setMessage("success");
		}else{
			response.setSuccess(false);
			response.setMessage("Add Risk Record Failure");
			response.setData(riskRecord);
		}
		
		return response;
		
	}
	
	/**
	 * 
	 * @param riskTracing
	 * @return
	 */
	@RequestMapping("/risk/tracing/create")
	@ResponseBody
	public ResponseMessage<RiskTracingDTO> createRiskTracing(@RequestBody RiskTracingDTO riskTracing){
		 ResponseMessage<RiskTracingDTO> response=new ResponseMessage<>();
		 if(riskTracing==null){
			 response.setSuccess(false);
			 response.setMessage("Add Risk Tracing Failure");
			 response.setData(riskTracing);
			 return response;
		 }
		 if(StringUtils.isEmpty(riskTracing.getRid())){
			 response.setSuccess(false);
			 response.setMessage("Please enter risk");
			 response.setData(riskTracing);
			 return response;
		 }
		 if(StringUtils.isEmpty(riskTracing.getUid())){
			 response.setSuccess(false);
			 response.setMessage("Please enter user");
			 response.setData(riskTracing);
			 return response;
		 }
		 RiskTracingDTO dto =service.addTracing(riskTracing);
		 if(dto!=null){
			 response.setData(dto);
			 response.setSuccess(true);
			 response.setMessage("success"); 
		 }else{
			 response.setSuccess(false);
			 response.setMessage("Add Risk Tracing Failure"); 
			 response.setData(riskTracing);
		 }
		 
		 return response;
	}
	
	/**
	 * 
	 * @param riskTracing
	 * @return
	 */
	@RequestMapping("/risk/tracing/remove")
	@ResponseBody
	public ResponseMessage<RiskTracingDTO> removeRiskTracing(@RequestBody RiskTracingDTO riskTracing){
		 ResponseMessage<RiskTracingDTO> response=new ResponseMessage<>();
		 if(riskTracing==null){
			 response.setSuccess(false);
			 response.setMessage("Remove Risk Tracing Failure");
			 return response;
		 }
		 if(StringUtils.isEmpty(riskTracing.getRid())){
			 response.setSuccess(false);
			 response.setMessage("Please enter risk");
			 return response;
		 }
		 if(StringUtils.isEmpty(riskTracing.getUid())){
			 response.setSuccess(false);
			 response.setMessage("Please enter user");
			 return response;
		 }
		 boolean success=service.removeTracing(riskTracing);
		 if(success){
			 response.setSuccess(true);
			 response.setMessage("success"); 
		 }else{
			 response.setSuccess(false);
			 response.setMessage("Add Risk Tracing Failure"); 
		 }
		 
		 return response;
	}

}
