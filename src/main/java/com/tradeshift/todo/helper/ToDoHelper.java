package com.tradeshift.todo.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradeshift.todo.dao.TaskDAO;
import com.tradeshift.todo.dao.UserDAO;
import com.tradeshift.todo.entity.Task;
import com.tradeshift.todo.entity.User;
import com.tradeshift.todo.webentity.CreateTaskRequest;
import com.tradeshift.todo.webentity.TaskItem;
import com.tradeshift.todo.webentity.TaskResponse;

@Component("todoHelper")
public class ToDoHelper {
	
	@Autowired
	private TaskDAO taskDao;
	
	@Autowired
	private UserDAO userDao;

	public List<Task> getTasksByUser(Long userId) throws SQLException{
		return taskDao.getTaskByUserId(userId);
	}
	
	
	public User getUser(String userGuid) throws SQLException{
		return userDao.getUser(userGuid);
		
	}
	
	public TaskResponse buildTaskResponse(List<Task> tasks){
		TaskResponse response = new TaskResponse();
		List<TaskItem> taskItems = new ArrayList<TaskItem>();
		for(Task task:tasks){
			TaskItem item = new TaskItem();
			item.setId(task.getTaskId());
			item.setTodoTask(task.getTask());
			item.setAssigneeId(String.valueOf(task.getAssigneeId()));
			item.setIsComplete(task.getComplete());
			taskItems.add(item);
		}
		response.setItems(taskItems);
		return response;
	}
	
	
	public Task getTaskById(Long taskId) throws SQLException{
		return taskDao.getTaskByTaskId(taskId);
	}
	
	public void updateTask(Long taskId, Long assigneeId, Long isComplete ) throws SQLException{
		Task task = new Task();
		task.setTaskId(taskId);
		task.setAssigneeId(assigneeId);
		task.setComplete(isComplete);
		
		taskDao.udpate(task);
	}
	
	public TaskResponse createTask(CreateTaskRequest request, Long userId) throws SQLException{
		Task task = new Task();
		task.setTask(request.getTodoTask());
		task.setAssigneeId(userId);
		task.setCreatorId(userId);
		Long taskId = taskDao.save(task);
		
		TaskResponse response = new TaskResponse();
		List<TaskItem> items = new ArrayList<TaskItem>();
		TaskItem item = new TaskItem();
		item.setAssigneeId(String.valueOf(userId));
		item.setId(taskId);
		item.setTodoTask(request.getTodoTask());
		items.add(item);
		response.setItems(items);
		
		return response;
	}
}
