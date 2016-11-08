package com.devops.utils;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCUtil {

    private static int lport;//本地端口
    private static String rhost;//远程MySQL服务器
    private static int rport;//远程MySQL服务端口
    private static String ssh_user;//SSH连接用户名
    private static String ssh_password;//SSH连接密码
    private static String ssh_host;//SSH服务器
    private static int ssh_port;//SSH访问端口
    private static String db_user;//SSH连接用户名
    private static String db_password;//SSH连接密码

    private static Connection conn = null;
    private static Logger LOGGER = LoggerFactory.getLogger(JDBCUtil.class);

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
            ssh_user        = prop.getProperty("ssh_user");
            ssh_password    = prop.getProperty("ssh_password");
            ssh_host        = prop.getProperty("ssh_host");
            ssh_port        = Integer.parseInt(prop.getProperty("ssh_port"));
            db_user         = prop.getProperty("db_user");
            db_password     = prop.getProperty("db_password");

            Class.forName("com.mysql.jdbc.Driver");

            JSch jsch = new JSch();
            Session session = jsch.getSession(ssh_user, ssh_host, ssh_port);
            session.setPassword(ssh_password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/risk", db_user, db_password);
        } catch (Exception e) {
            LOGGER.error("context", e);
        } finally {
            System.out.println("MySQL has been connected!");
        }
    }

//    public static void sql() {
//        Connection conn = null;
//        ResultSet rs = null;
//        Statement st = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/risk", "root", "325632");
//            st = conn.createStatement();
////            String sql = "SELECT COUNT(1) FROM All";
////            rs = st.executeQuery(sql);
////            while (rs.next())
////                System.out.println(rs.getString(1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //rs.close();st.close();conn.close();
//        }
//    }

//    public static void main(String[] args) throws SQLException {
//        Connection conn = JDBCUtil.getConnection();
//        Statement st = conn.createStatement();
//        String sql = "select * from user";
//        ResultSet rs = st.executeQuery(sql);
//        while (rs.next()) {
//            System.out.println(123);
//        }
//    }
}