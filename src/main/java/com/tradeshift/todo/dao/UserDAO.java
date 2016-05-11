package com.tradeshift.todo.dao;

import java.sql.SQLException;

import com.tradeshift.todo.entity.User;

public interface UserDAO {

	
	public User getUser(String userGuid) throws SQLException;
}
