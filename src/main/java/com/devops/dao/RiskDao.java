package com.devops.dao;

import com.devops.entity.Risk;
import com.devops.entity.RiskRecord;
import com.devops.entity.RiskTracing;

import java.util.List;

/**
 * Created by super on 2016/11/6.
 */
public interface RiskDao {

    public Risk getRiskByRiskID(String rid);

    public Risk getRiskByTeamID(String tid);

    public List<RiskRecord> getRecords(String rid);

    public RiskRecord getRecord(String rrid);

    public void addRecord(RiskRecord riskRecord);

    //remember to add tracing list & record
    public void addRisk(Risk risk);

    public void addTracing(RiskTracing riskTracing);
}
