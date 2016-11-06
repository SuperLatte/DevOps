package controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.ProjectDTO;

@RestController
@RequestMapping("/project")
@EnableAutoConfiguration
public class ProjectController {
	
	@RequestMapping("/{id}")
	public ProjectDTO view(@PathVariable("id") Long id) {  
        
		ProjectDTO project=new ProjectDTO();
		
        return project;  
    }
}
