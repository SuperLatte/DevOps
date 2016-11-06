package com.devops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by super on 2016/11/6.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "hello";
    }

}
