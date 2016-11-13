package com.devops.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by super on 2016/11/6.
 */
@Controller
public class IndexController {

    @Autowired
    HttpServletRequest request;

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

    public static final String RISK_DETAIL="riskDetail";


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


    @RequestMapping("/riskDetail")
    public String riskDetail() {
        return RISK_DETAIL;
    }



    @RequestMapping("/logout")
    public Map<String, Object> logout() {
        Map<String, Object> data = new HashMap<>();
        request.getSession().invalidate();
        data.put("url", "./login");
        return data;
    }
}
