package com.devops.dao.impl;

import com.devops.dao.RiskDao;
import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
import com.devops.entity.RiskTracing;
import com.devops.entity.User;
import com.devops.utils.JDBCUtil;
import com.devops.utils.TimeGetter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by super on 2016/11/6.
 */
public class RiskDaoImpl implements RiskDao {

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public RiskDaoImpl() throws SQLException {
        connection = JDBCUtil.getConnection();
        statement = connection.createStatement();
    }

    @Override
    public Risk getRiskByRiskID(String rid) throws SQLException {
        statement = connection.createStatement();
        String sql = "select * from risk where rid=" + rid;
        resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            Risk risk = tranRisk(resultSet);
            statement.close();
            return risk;
        }
        return null;
    }

    @Override
    public List<Risk> getRiskByTeamID(String tid) throws SQLException {
        statement = connection.createStatement();
        String sql = "select * from risk where tid=" + tid;
        resultSet = statement.executeQuery(sql);
        List<Risk> risks = new ArrayList<>();
        while (resultSet.next()) {
            risks.add(tranRisk(resultSet));
        }
        statement.close();
        return risks;
    }

    @Override
    public List<RiskRecord> getRecords(String rid) throws SQLException {
        statement = connection.createStatement();
        String sql = "select * from risk_record where rid=" + rid;
        resultSet = statement.executeQuery(sql);
        List<RiskRecord> riskRecords = new ArrayList<>();
        while (resultSet.next()) {
            riskRecords.add(tranRiskRecord(resultSet));
        }
        statement.close();
        return riskRecords;
    }

    @Override
    public RiskRecord getRecord(String rrid) throws SQLException {
        statement = connection.createStatement();
        String sql = "select * from risk_record where rrid=" + rrid;
        resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            RiskRecord record = tranRiskRecord(resultSet);
            statement.close();
            return record;
        }
        return null;
    }

    @Override
    public void addRecord(RiskRecord riskRecord) throws SQLException {
        int time = TimeGetter.getCurrentTime();
        riskRecord.setCreateTime(time);

        statement = connection.createStatement();
        String sql = "insert into risk_record(rid, createtime, content, possibility, affection, `trigger`, trace_userid) values("
                + riskRecord.getRid() + ","
                + riskRecord.getCreateTime() + ",'"
                + riskRecord.getContent() + "',"
                + riskRecord.getPossibility() + ","
                + riskRecord.getAffection() + ",'"
                + riskRecord.getTrigger() + "',"
                + riskRecord.getTraceUserid() + ")";
        statement.execute(sql);

        String rid = riskRecord.getRid();
        int result = statement.executeUpdate("update risk set updatetime=" + time + " where rid=" + rid);
        System.out.println(result);
    }

    @Override
    public int addRisk(Risk risk) throws SQLException {
        int time = TimeGetter.getCurrentTime();
        risk.setUpdateTime(time);
        risk.setCreateTime(time);

        statement = connection.createStatement();
        String sql = "insert into risk(tid, name, createtime, updatetime) values("
                + risk.getTid() + ",'"
                + risk.getName() + "',"
                + risk.getCreateTime() + ","
                + risk.getUpdateTime() + ")";
        boolean result = statement.execute(sql);
        resultSet = statement.executeQuery("SELECT MAX(rid) from risk");
        if (resultSet.next()) {
            int rid = resultSet.getInt(1);
            statement.close();
            return rid;
        }
        statement.close();
        return -1;
    }

    @Override
    public void addTracing(RiskTracing riskTracing) throws SQLException {
        statement = connection.createStatement();
        String sql = "insert into risk_tracing(rid, uid) values("
                + riskTracing.getRid() + ","
                + riskTracing.getUid() + ")";
        boolean result = statement.execute(sql);
        statement.close();
    }

    private Risk tranRisk(ResultSet resultSet) throws SQLException {
        Risk risk = new Risk();
        risk.setRid(resultSet.getString("rid"));
        risk.setTid(resultSet.getString("tid"));
        risk.setName(resultSet.getString("name"));
        risk.setCreateTime(resultSet.getInt("createtime"));
        risk.setUpdateTime(resultSet.getInt("updateTime"));
        return risk;
    }

    private RiskRecord tranRiskRecord(ResultSet resultSet) throws SQLException {
        RiskRecord riskRecord = new RiskRecord();
        riskRecord.setRrid(resultSet.getString("rrid"));
        riskRecord.setRid(resultSet.getString("rid"));
        riskRecord.setCreateTime(resultSet.getInt("createtime"));
        riskRecord.setContent(resultSet.getString("content"));
        riskRecord.setPossibility(resultSet.getInt("possibility"));
        riskRecord.setAffection(resultSet.getInt("affection"));
        riskRecord.setTrigger(resultSet.getString("trigger"));
        riskRecord.setTraceUserid(resultSet.getString("trace_userid"));
        return riskRecord;
    }

//    public static void main(String[] args) throws SQLException {
////        RiskTracing riskTracing = new RiskTracing();
////        riskTracing.setRid("1");
////        riskTracing.setUid("1");
////        new RiskDaoImpl().addTracing(riskTracing);
//
////        Risk risk = new Risk();
////        risk.setTid("1");
////        risk.setName("risk12");
////        int i = new RiskDaoImpl().addRisk(risk);
////        System.out.println(i);
//
//        RiskRecord record = new RiskRecord();
//        record.setRid("5");
//        record.setContent("content");
//        record.setPossibility(1);
//        record.setAffection(2);
//        record.setTraceUserid("2");
//        record.setTrigger("trigger");
//        new RiskDaoImpl().addRecord(record);
//    }
}
