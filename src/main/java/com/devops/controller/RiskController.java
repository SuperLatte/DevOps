package com.devops.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devops.entity.Risk;
import com.devops.service.RiskService;

@RestController
@RequestMapping("/risk")
@EnableAutoConfiguration
public class RiskController {
	
	@Autowired
	RiskService service;
	
	@RequestMapping("/{id}")
	public Risk view(@PathParam(value="id") String rid) {  
        
		Risk risk=service.getRiskById(rid);
		
        return risk;  
    }
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public void create( @ModelAttribute("risk") Risk risk){
		service.add(risk);
	}

}
