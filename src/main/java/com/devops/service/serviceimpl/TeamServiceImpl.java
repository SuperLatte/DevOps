package com.devops.service.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.dao.impl.TeamDaoImpl;
import com.devops.dto.TeamDTO;
import com.devops.dto.UserDTO;
import com.devops.entity.Team;
import com.devops.entity.User;
import com.devops.service.TeamService;
import com.devops.utils.EntityToDtoUtil;

/**
 * 
 * @author lujxu
 *
 */
@Service
public class TeamServiceImpl implements TeamService{

	@Autowired
	TeamDaoImpl teamDao;
	
	Logger logger=Logger.getLogger("com.devops.service.serviceImpl.TeamServiceImpl");
	
	@Override
	public List<UserDTO> getAllUserByManagrId(String mid) { 
		Team team=new Team();
		List<UserDTO> list=new ArrayList<>();
		try {
			team=teamDao.getTeamByManagerID(mid);
			if(team==null) 
				return new ArrayList<>();
			List<User> user=teamDao.getTeamMembers(team.getTid());
			if(user==null) 
				return new ArrayList<>();
			for(User u:user){
				list.add(EntityToDtoUtil.userToUserDTO(u));
			}
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		return list;
	}

	@Override
	public TeamDTO getTeamByManagerId(String mid) {
		TeamDTO teamDTO=new TeamDTO();
		try {
			Team team=teamDao.getTeamByManagerID(mid);
			if(team==null) 
				return new TeamDTO();
			teamDTO=EntityToDtoUtil.teamToTeamDTO(team);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		return teamDTO;
	}

	@Override
	public TeamDTO getTeamById(String tid) {
		TeamDTO teamDTO=new TeamDTO();
		try {
			Team team=teamDao.getTeamByTeamID(tid);
			if(team==null) 
				return new TeamDTO();
			teamDTO=EntityToDtoUtil.teamToTeamDTO(team);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}
		return teamDTO;
	}

}
