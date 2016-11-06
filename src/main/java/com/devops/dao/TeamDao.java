package com.devops.dao;

import com.devops.entity.Team;
import com.devops.entity.User;

import java.util.List;

/**
 * Created by super on 2016/11/6.
 */
public interface TeamDao {

    public Team getTeamByTeamID(String tid);

    public Team getTeamByManagerID(String uid);

    public List<User> getTeamMembers(String tid);

}
