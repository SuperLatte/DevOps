package com.devops.utils;

import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
import com.devops.entity.Team;
import com.devops.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by super on 2016/11/13.
 */
public class ResultSetTranUtil {

    public static Risk tranRisk(ResultSet resultSet) throws SQLException {
        Risk risk = new Risk();
        risk.setRid(resultSet.getString("rid"));
        risk.setTid(resultSet.getString("tid"));
        risk.setName(resultSet.getString("name"));
        risk.setCreateTime(resultSet.getInt("createtime"));
        risk.setUpdateTime(resultSet.getInt("updateTime"));
        risk.setStatus(resultSet.getInt("status"));
        risk.setDescription(resultSet.getString("description"));
        return risk;
    }

    public static RiskRecord tranRiskRecord(ResultSet resultSet) throws SQLException {
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

    public static User tranUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUid(resultSet.getString("uid"));
        user.setName(resultSet.getString("name"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setLevel(resultSet.getInt("level"));
        return user;
    }

    public static Team tranTeam(ResultSet resultSet) throws SQLException {
        Team team = new Team();
        team.setTid(resultSet.getString("tid"));
        team.setName(resultSet.getString("name"));
        team.setManagerId(resultSet.getString("manager_id"));
        return team;
    }
}
