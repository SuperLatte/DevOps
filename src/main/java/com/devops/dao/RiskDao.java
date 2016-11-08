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
	/**
	 * 
	 * @param rid
	 * @return
	 * @throws SQLException
	 */
    public Risk getRiskByRiskID(String rid) throws SQLException;

    /**
     * 
     * @param tid
     * @return
     * @throws SQLException
     */
    public List<Risk> getRiskByTeamID(String tid) throws SQLException;

    /**
     * 
     * @param uid
     * @return
     * @throws SQLException
     */
    public List<Risk> getRiskByUserID(String uid) throws SQLException;

    /**
     * 
     * @param rid
     * @return
     * @throws SQLException
     */
    public List<RiskRecord> getRecords(String rid) throws SQLException;

    /**
     * 
     * @param rrid
     * @return
     * @throws SQLException
     */
    public RiskRecord getRecord(String rrid) throws SQLException;

    /**
     * 
     * @param riskRecord
     * @return
     * @throws SQLException
     */
    public int addRecord(RiskRecord riskRecord) throws SQLException;

    //remember to add tracing list & record
    /**
     * 
     * @param risk
     * @return
     * @throws SQLException
     */
    public int addRisk(Risk risk) throws SQLException;

    /**
     * 
     * @param riskTracing
     * @return
     * @throws SQLException
     */
    public boolean addTracing(RiskTracing riskTracing) throws SQLException;

    /**
     * 
     * @param riskTracing
     * @return
     * @throws SQLException
     */
    public boolean deleteTracing(RiskTracing riskTracing) throws SQLException;

    /**
     * 
     * @param rid
     * @return
     * @throws SQLException
     */
    public List<RiskTracing> getTracingByRiskID(String rid) throws SQLException;

    /**
     * 
     * @param risk
     * @return
     * @throws SQLException
     */
    public boolean editRisk(Risk risk) throws SQLException;
}
