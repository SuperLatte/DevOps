package com.devops.dao.impl;

import com.devops.dao.TeamDao;
import com.devops.entity.Team;
import com.devops.entity.User;
import com.devops.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Created by super on 2016/11/6.
 */
@Repository
public class TeamDaoImpl implements TeamDao{

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public TeamDaoImpl() throws SQLException {
        connection = JDBCUtil.getConnection();
        statement = connection.createStatement();
    }

    @Override
    public Team getTeamByTeamID(String tid) throws SQLException {
        statement = connection.createStatement();
        String sql = "select * from team where tid=" + tid;
        resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            Team team = tranTeam(resultSet);
            statement.close();
            return team;
        }
        return null;
    }

    @Override
    public Team getTeamByManagerID(String uid) throws SQLException {
        statement = connection.createStatement();
        String sql = "select * from team where manager_id=" + uid;
        resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            Team team = tranTeam(resultSet);
            statement.close();
            return team;
        }
        return null;
    }

    @Override
    public List<User> getTeamMembers(String tid) throws SQLException {
        statement = connection.createStatement();
        String sql = "SELECT u.* FROM user u, team_relationship r where r.uid=u.uid and r.tid=" + tid;
        resultSet = statement.executeQuery(sql);
        List<User> user = new ArrayList<>();
        while (resultSet.next()) {
            user.add(tranUser(resultSet));
        }
        statement.close();
        return user;
    }

    private Team tranTeam(ResultSet resultSet) throws SQLException {
        Team team = new Team();
        team.setTid(resultSet.getString("tid"));
        team.setName(resultSet.getString("name"));
        team.setManager_id(resultSet.getString("manager_id"));
        return team;
    }

    private User tranUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUid(resultSet.getString("uid"));
        user.setName(resultSet.getString("name"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setLevel(resultSet.getInt("level"));
        return user;
    }

}
