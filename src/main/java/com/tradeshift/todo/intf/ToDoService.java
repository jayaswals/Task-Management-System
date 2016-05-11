package com.tradeshift.todo.intf;


import com.tradeshift.todo.webentity.CreateTaskRequest;
import com.tradeshift.todo.webentity.TaskResponse;
import com.tradeshift.todo.webentity.UpdateTaskRequest;



public interface ToDoService {

	
	
	public TaskResponse getTasks(String userGuid, String taskId);
	
	
	
	public TaskResponse updateTask(String userGuid, UpdateTaskRequest request);


	
	public TaskResponse createTask(String userGuid, CreateTaskRequest request);

}
