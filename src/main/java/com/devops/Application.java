package com.devops;

import java.io.FileInputStream;
import java.util.logging.Logger;

/**
 * Created by puddingtea07 on 11/6/16.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import com.devops.utils.JDBCUtil;
/**
 * 
 * @author lujxu
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer{
	
	/**
	 * 
	 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {


        SpringApplication.run(Application.class, args);
    	Logger logger=Logger.getLogger("com.devops.Application");
    	logger.info("Springboot has been started!");
        JDBCUtil.getConnection();
    }
}
