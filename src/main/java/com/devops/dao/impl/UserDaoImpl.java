package com.devops.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.devops.dao.UserDao;
import com.devops.entity.User;
import com.devops.utils.JDBCUtil;

/**
 * Created by super on 2016/11/6.
 */
@Repository
public class UserDaoImpl implements UserDao{

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public UserDaoImpl() throws SQLException {
        connection = JDBCUtil.getConnection();
        statement = connection.createStatement();
    }

    @Override
    public User getUser(String username, String password) throws SQLException {
        statement = connection.createStatement();
        String sql = "select * from user where username='" + username + "'and password='" + password + "'";
        resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            User user = tranUser(resultSet);
            statement.close();
            return user;
        }
        return null;
    }

    @Override
    public User getUser(String uid) throws SQLException {
        statement = connection.createStatement();
        String sql = "select * from user where uid=" + uid;
        resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            User user = tranUser(resultSet);
            statement.close();
            return user;
        }
        return null;
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
