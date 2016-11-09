package com.devops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by super on 2016/11/6.
 */
@Controller
public class IndexController {

	/**hello page**/
	public static final String HELLO="hello";
	/**
	 * login page
	 */
	public static final String LOGIN="login";
	/**
	 * myRiskEntries
	 */
	public static final String MY_RISK_ENTRIES="myRiskEntries";
	/**
	 * newRisk
	 */
	public static final String NEW_RISK="newRisk";
	
	/**
	 * 
	 * @return
	 */
    @RequestMapping("/")
    public String index() {
        return LOGIN;
    }

    /**
     * 
     * @return
     */
    @RequestMapping("/login")
    public String loginPage() {
        return LOGIN;
    }

    /**
     * 
     * @return
     */
    @RequestMapping("/myProjects")
    public String projects() {
        return MY_RISK_ENTRIES;
    }

    /**
     * 
     * @return
     */
    @RequestMapping("/newRisk")
    public String newRisk() {
        return NEW_RISK;
    }

}
