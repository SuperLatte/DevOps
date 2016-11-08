package com.devops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by super on 2016/11/6.
 */
@Controller
public class IndexController {

	public static String hello="hello";
	public static String login="login";
	public static String myRiskEntries="myRiskEntries";
	
	/**
	 * 
	 * @return
	 */
    @RequestMapping("/")
    public String index() {
        return hello;
    }

    /**
     * 
     * @return
     */
    @RequestMapping("/login")
    public String loginPage() {
        return login;
    }

    /**
     * 
     * @return
     */
    @RequestMapping("/myProjects")
    public String projects() {
        return myRiskEntries;
    }

    /**
     * 
     * @return
     */
    @RequestMapping("/newRisk")
    public String newRisk() {
        return "newRisk";
    }

}
