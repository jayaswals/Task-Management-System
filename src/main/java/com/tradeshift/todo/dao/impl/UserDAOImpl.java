package com.tradeshift.todo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradeshift.todo.dao.UserDAO;
import com.tradeshift.todo.entity.User;

@Component("userDao")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private DerbyConnection connection;
	
	@Override
	public User getUser(String userGuid) throws SQLException{
		Statement stmt = connection.getConn().createStatement();
		ResultSet rs=stmt.executeQuery("Select * from Users where user_guid='"+userGuid+"'");
		
		User user = new User();
		if(rs.next()){
			  do {
				user.setUserId(rs.getLong("user_id"));
				user.setName(rs.getString("user_name"));
				user.setUserId(rs.getLong("user_id"));
			  }while (rs.next());
			  
			rs.close();
			stmt.close();
			return user;
		}else{
			rs.close();
			stmt.close();
			return null;
		}
		
	}

}
