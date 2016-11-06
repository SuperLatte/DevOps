package com.devops.dao.impl;

import com.devops.dao.RiskDao;
import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
import com.devops.entity.RiskTracing;
import com.devops.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public Risk getRiskByRiskID(String rid) {
        return null;
    }

    @Override
    public Risk getRiskByTeamID(String tid) {
        return null;
    }

    @Override
    public List<RiskRecord> getRecords(String rid) {
        return null;
    }

    @Override
    public RiskRecord getRecord(String rrid) {
        return null;
    }

    @Override
    public void addRecord(RiskRecord riskRecord) {

    }

    @Override
    public void addRisk(Risk risk) {

    }

    @Override
    public void addTracing(RiskTracing riskTracing) {

    }
}
