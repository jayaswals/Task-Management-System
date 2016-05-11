package com.tradeshift.todo.dao;

import java.sql.SQLException;
import java.util.List;

import com.tradeshift.todo.entity.Task;

public interface TaskDAO {

	public List<Task> getTaskByUserId(Long userId) throws SQLException;
	
	public Task getTaskByTaskId(Long taskId) throws SQLException;
	
	public Long save(Task task) throws SQLException;
	
	public void udpate(Task task) throws SQLException;
	
}
