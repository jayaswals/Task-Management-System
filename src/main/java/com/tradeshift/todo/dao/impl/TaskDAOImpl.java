package com.tradeshift.todo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradeshift.todo.dao.TaskDAO;
import com.tradeshift.todo.entity.Task;

@Component("taskDao")
public class TaskDAOImpl implements TaskDAO {

	
	@Autowired
	private DerbyConnection connection;
	
	@Override
	public List<Task> getTaskByUserId(Long userId) throws SQLException{
		Statement stmt = connection.getConn().createStatement();
		ResultSet rs=stmt.executeQuery("Select * from Tasks where owner_id="+userId);
		
		List<Task> tasks = new ArrayList<Task>();
		if(rs!=null ){
			  while (rs.next()) {
				  Task task= new Task();
			      task.setAssigneeId(rs.getLong("owner_id"));
			      task.setComplete(rs.getLong("is_complete"));
			      task.setCreatorId(rs.getLong("created_by"));
			      task.setTask(rs.getString("task_name"));
			      task.setTaskId(rs.getLong("task_id"));
			      tasks.add(task);
			}
		}
		rs.close();
		stmt.close();
		
		return tasks;
	}

	@Override
	public Task getTaskByTaskId(Long taskId) throws SQLException{
		Statement stmt = connection.getConn().createStatement();
		ResultSet rs=stmt.executeQuery("Select * from Tasks where task_id="+taskId);
		
		Task task = new Task();
		if(rs!=null ){
			  while (rs.next()) {
			      task.setAssigneeId(rs.getLong("owner_id"));
			      task.setComplete(rs.getLong("is_complete"));
			      task.setCreatorId(rs.getLong("created_by"));
			      task.setTask(rs.getString("task_name"));
			      task.setTaskId(rs.getLong("task_id"));
			}
		}
		rs.close();
		stmt.close();
		return task;
	}

	@Override
	public Long save(Task task) throws SQLException{
//		Statement stmt = connection.getConn().createStatement();
//		stmt.executeUpdate("INSERT INTO Tasks (task_name, created_by, owner_id, is_complete) VALUES ('"+task.getTask()+"', "+task.getCreatorId()+","+task.getAssigneeId()+", 0");
		
		
		String insert = "INSERT INTO Tasks (task_name, created_by, owner_id," +
		        " is_complete) VALUES (?,?,?,?)";

		PreparedStatement pstmt = connection.getConn().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, task.getTask()); 
		pstmt.setLong(2, task.getCreatorId());
		pstmt.setLong(3, task.getAssigneeId()); 
		pstmt.setLong(4, 0l);
		pstmt.execute();
		
		
		ResultSet res = pstmt.getGeneratedKeys();
		Long taskId =0l;
		while(res.next()){
			taskId = res.getLong(1);
		}
		pstmt.close();
		res.close();
		return taskId;
	}

	@Override
	public void udpate(Task task) throws SQLException{
		Statement stmt = connection.getConn().createStatement();
		StringBuilder query = new StringBuilder();
		query.append("UPDATE Tasks");
		if(task.getAssigneeId()!=null && task.getAssigneeId()>0){
			query.append(" set owner_id=");
			query.append(task.getAssigneeId());
		}else if(task.getComplete()!=null && task.getComplete()==1){
			query.append(" set is_complete=");
			query.append(task.getComplete());
		}
		query.append(" where task_id=");
		query.append(task.getTaskId());
		stmt.executeUpdate(query.toString());
		stmt.close();

	}

	
}
