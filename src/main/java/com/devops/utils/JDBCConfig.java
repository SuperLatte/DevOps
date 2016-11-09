package com.devops.utils;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:config.properties")
public class JDBCConfig {

    private static Logger logger = LoggerFactory.getLogger(JDBCConfig.class);

    @Value("${lport}")
    private int lport;//本地端口
    @Value( "${rhost}")
    private String rhost;//远程MySQL服务器
    @Value( "${rport}")
    private int rport;//远程MySQL服务端口
    @Value( "${ssh_user}")
    private String ssh_user;//SSH连接用户名
    @Value( "${ssh_password}")
    private String ssh_password;//SSH连接密码
    @Value( "${ssh_host}")
    private String ssh_host;//SSH服务器
    @Value( "${ssh_port}")
    private int ssh_port;//SSH访问端口
    @Value( "${db_user}")
    private String db_user;//SSH连接用户名
    @Value( "${db_password}")
    private String db_password;//SSH连接密码

    @Bean
    public Connection connection() throws JSchException, SQLException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(ssh_user, ssh_host, ssh_port);
        session.setPassword(ssh_password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        logger.info(session.getServerVersion());//这里打印SSH服务器版本信息
        int assingedPort = session.setPortForwardingL(lport, rhost, rport);
        logger.info("localhost:" + assingedPort + " -> " + rhost + ":" + rport);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:"+lport+"/risk", db_user, db_password);
        logger.info("MySQL has been connected!");
        return connection;
    }
}