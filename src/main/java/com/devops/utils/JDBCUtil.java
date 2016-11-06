package com.devops.utils;

import java.sql.*;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JDBCUtil {

    private static int lport = 3306;//本地端口
    private static String rhost = "139.129.30.61";//远程MySQL服务器
    private static int rport = 3306;//远程MySQL服务端口
    private static String user = "root";//SSH连接用户名
    private static String password = "3256kugH";//SSH连接密码
    private static String host = "139.129.30.61";//SSH服务器
    private static int port = 22;//SSH访问端口
    private static Connection conn = null;

    public static Connection getConnection() {
        return conn;
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/risk", "root", "325632");
        } catch (Exception e) {
            System.out.println("Failed to connect to MySQL!");
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