package com.devops.dao;

import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
import com.devops.entity.RiskTracing;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by super on 2016/11/6.
 */
public interface RiskDao {

    public Risk getRiskByRiskID(String rid) throws SQLException;

    public List<Risk> getRiskByTeamID(String tid) throws SQLException;

    public List<Risk> getRiskByUserID(String uid) throws SQLException;

    public List<RiskRecord> getRecords(String rid) throws SQLException;

    public RiskRecord getRecord(String rrid) throws SQLException;

    public void addRecord(RiskRecord riskRecord) throws SQLException;

    //remember to add tracing list & record
    public int addRisk(Risk risk) throws SQLException;

    public void addTracing(RiskTracing riskTracing) throws SQLException;

    public void editRisk(Risk risk) throws SQLException;
}
