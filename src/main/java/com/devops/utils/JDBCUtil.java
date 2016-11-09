package com.devops.utils;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author lujxu
 *
 */
public class JDBCUtil {

    private static int lport;//本地端口
    private static String rhost;//远程MySQL服务器
    private static int rport;//远程MySQL服务端口
    private static String sshUser;//SSH连接用户名
    private static String sshPassword;//SSH连接密码
    private static String sshHost;//SSH服务器
    private static int sshPort;//SSH访问端口
    private static String dbUser;//SSH连接用户名
    private static String dbPassword;//SSH连接密码

    private static Connection conn = null;
    private static Logger logger = LoggerFactory.getLogger(JDBCUtil.class);

    public static Connection getConnection() {
        return conn;
    }

    static {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.properties"));

            lport           = Integer.parseInt(prop.getProperty("lport"));
            rhost           = prop.getProperty("rhost");
            rport           = Integer.parseInt(prop.getProperty("rport"));
            sshUser        = prop.getProperty("ssh_user");
            sshPassword    = prop.getProperty("ssh_password");
            sshHost        = prop.getProperty("ssh_host");
            sshPort        = Integer.parseInt(prop.getProperty("ssh_port"));
            dbUser         = prop.getProperty("db_user");
            dbPassword     = prop.getProperty("db_password");

            Class.forName("com.mysql.jdbc.Driver");

            JSch jsch = new JSch();
            Session session = jsch.getSession(sshUser, sshHost, sshPort);
            session.setPassword(sshPassword);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            logger.info(session.getServerVersion());//这里打印SSH服务器版本信息
            int assingedPort = session.setPortForwardingL(lport, rhost, rport);
            logger.info("localhost:" + assingedPort + " -> " + rhost + ":" + rport);

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/risk", dbUser, dbPassword);
        } catch (Exception e) {
            logger.error("context", e);
        } finally {
        	logger.info("MySQL has been connected!");
        }
    }

}