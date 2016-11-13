package com.devops.dao.impl;

import java.sql.*;

import com.devops.utils.ResultSetTranUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devops.dao.UserDao;
import com.devops.entity.User;

/**
 * Created by super on 2016/11/6.
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    /**
     * 
     * @throws SQLException
     */
    public UserDaoImpl() throws SQLException {
        //do nothing because of autowiring
    }

    @Override
    public User getUser(String username, String password) throws SQLException {

        preparedStatement = connection.prepareStatement("select * from user where username=? and password=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User user = ResultSetTranUtil.tranUser(resultSet);
            preparedStatement.close();
            return user;
        }
        return null;
    }

    @Override
    public User getUser(String uid) throws SQLException {

        preparedStatement = connection.prepareStatement("select * from user where uid=?");
        preparedStatement.setInt(1, Integer.parseInt(uid));
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User user = ResultSetTranUtil.tranUser(resultSet);
            preparedStatement.close();
            return user;
        }
        return null;
    }

}
