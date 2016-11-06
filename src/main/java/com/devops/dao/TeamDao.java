package com.devops.dao;

import com.devops.entity.Team;
import com.devops.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by super on 2016/11/6.
 */
public interface TeamDao {

    public Team getTeamByTeamID(String tid) throws SQLException;

    public Team getTeamByManagerID(String uid) throws SQLException;

    public List<User> getTeamMembers(String tid) throws SQLException;

}
