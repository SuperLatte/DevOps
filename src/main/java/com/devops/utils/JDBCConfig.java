package com.devops.utils;

import java.sql.*;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yidzhou
 */
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
    private String sshUser;//SSH连接用户名
    @Value( "${ssh_password}")
    private String sshPassword;//SSH连接密码
    @Value( "${ssh_host}")
    private String sshHost;//SSH服务器
    @Value( "${ssh_port}")
    private int sshPort;//SSH访问端口
    @Value( "${db_user}")
    private String dbUser;//SSH连接用户名
    @Value( "${db_password}")
    private String dbPassword;//SSH连接密码

    /**
     *
     * @return
     * @throws Exception
     */
    @Bean
    public Connection connection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        JSch jsch = new JSch();
        Session session = jsch.getSession(sshUser, sshHost, sshPort);
        session.setPassword(sshPassword);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        logger.info(session.getServerVersion());//这里打印SSH服务器版本信息
        int assingedPort = session.setPortForwardingL(lport, rhost, rport);
        logger.info("localhost:" + assingedPort + " -> " + rhost + ":" + rport);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:"+lport+"/risk", dbUser, dbPassword);
        logger.info("MySQL has been connected!");
        return connection;
    }
}