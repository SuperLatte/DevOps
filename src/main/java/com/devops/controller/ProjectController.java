package com.devops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devops.dto.ProjectDTO;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@RequestMapping("/")
	public ProjectDTO view() {  
        
		ProjectDTO project=new ProjectDTO();
		project.setId(1);
		project.setName("test");
		project.setCreatedBy("hh");
		project.setCreateTime("ggg");
		project.setUpdateTime("myname");
        return project;  
    }
	
	@RequestMapping(value="/testCreate",method=RequestMethod.POST)
	public void create( @ModelAttribute("project") ProjectDTO project){
		System.out.println(project.toString());
	}
	
}
