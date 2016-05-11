package com.tradeshift.todo.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradeshift.todo.entity.Task;
import com.tradeshift.todo.entity.User;
import com.tradeshift.todo.helper.TaskEnum;
import com.tradeshift.todo.helper.ToDoHelper;
import com.tradeshift.todo.helper.Validator;
import com.tradeshift.todo.intf.ToDoService;
import com.tradeshift.todo.webentity.CreateTaskRequest;
import com.tradeshift.todo.webentity.Error;
import com.tradeshift.todo.webentity.TaskResponse;
import com.tradeshift.todo.webentity.UpdateTaskRequest;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1")
@Component("todoService")
public class ToDoServiceImpl implements ToDoService {

	private static final String UPDATE_TASK = "updateTask";

	private static final String CREATE_TASK = "createTask";

	private static final String TODO_SERVICE = "todoService";

	private static final String GET_TASKS = "getTasks";

	Logger log = LoggerFactory.getLogger(ToDoServiceImpl.class);
	
	@Autowired 
	private ToDoHelper todoHelper;
	
	@Autowired
	private Validator validator;
	
	
	@GET
	@Path("/")
	public TaskResponse getTasks(@QueryParam("userGuid")String userGuid, @QueryParam("taskId") String taskId) {
		TaskResponse response  = new TaskResponse();
		List<com.tradeshift.todo.webentity.Error> errors = new ArrayList<com.tradeshift.todo.webentity.Error>();
		
		try{
			response.setErrors(errors);
		
		if(StringUtils.isBlank(userGuid)){
			Error error = new Error(TaskEnum.INPUT_EXCEPTION.value(), TaskEnum.NO_USER_GUID.value(), "User Guid not present", TaskEnum.USER_GUID.value());
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid is null",new Object[]{TODO_SERVICE, GET_TASKS});
			return response;
		}
		User user = todoHelper.getUser(userGuid);
		if(user==null){
			Error error = new Error(TaskEnum.INPUT_EXCEPTION.value(), TaskEnum.USER_NOT_FOUND.value(), "User not Found", TaskEnum.USER_GUID.value());
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid={} does not exist.",new Object[]{TODO_SERVICE, GET_TASKS, userGuid});
			return response;
		}
		if(!StringUtils.isBlank(taskId) ){
			List<Task> tempTasks= new ArrayList<Task>();
			tempTasks.add(todoHelper.getTaskById(Long.valueOf(taskId)));
			response = todoHelper.buildTaskResponse(tempTasks);
		}else{
			List<Task> userTasks= todoHelper.getTasksByUser(user.getUserId());
			if(userTasks!=null && userTasks.size()>0){
				response = todoHelper.buildTaskResponse(userTasks);
			}
		}
		}catch(SQLException sqle){
			Error error = new Error(TaskEnum.SYSTEM_EXCEPTION.value(), sqle.getMessage(), sqle.getMessage(), "");
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid={} taskId={} . SQLException occurred.",new Object[]{TODO_SERVICE, GET_TASKS, userGuid, taskId, sqle});
			
		}catch(Exception ex){
			Error error = new Error(TaskEnum.SYSTEM_EXCEPTION.value(), ex.getMessage(), ex.getMessage(), "");
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid={} taskId={} . Exception occurred.",new Object[]{TODO_SERVICE, GET_TASKS, userGuid,taskId, ex});

		}
		return response;
	}

	@PUT
	@Path("/")
	public TaskResponse updateTask(@QueryParam("userGuid")String userGuid, UpdateTaskRequest request) {
		TaskResponse response  = new TaskResponse();
		
		List<com.tradeshift.todo.webentity.Error> errors = new ArrayList<com.tradeshift.todo.webentity.Error>();
		response.setErrors(errors);
		if(StringUtils.isBlank(userGuid)){
			Error error = new Error(TaskEnum.INPUT_EXCEPTION.value(), TaskEnum.NO_USER_GUID.value(), "User Guid not present", TaskEnum.USER_GUID.value());
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid is null",new Object[]{TODO_SERVICE, UPDATE_TASK});
			return response;
		}
		try{
			User user = todoHelper.getUser(userGuid);
			if(user==null){
				Error error = new Error(TaskEnum.INPUT_EXCEPTION.value(), TaskEnum.USER_NOT_FOUND.value(), "User not Found", TaskEnum.USER_GUID.value());
				errors.add(error);
				log.error("api_resource={} api_method={} userGuid={} does not exist.",new Object[]{TODO_SERVICE, UPDATE_TASK, userGuid});
				return response;
			}
			validator.validate(request);
			todoHelper.updateTask(request.getTaskId(), request.getAssigneeId(), request.getIsComplete());
		}catch(SQLException sqle){
			Error error = new Error(TaskEnum.SYSTEM_EXCEPTION.value(), sqle.getMessage(), sqle.getMessage(), "");
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid={} . SQLException occurred.",new Object[]{TODO_SERVICE, UPDATE_TASK, userGuid, sqle});
		}catch(Exception ex){
			Error error = new Error(TaskEnum.SYSTEM_EXCEPTION.value(), ex.getMessage(), ex.getMessage(), "");
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid={} . Exception occurred.",new Object[]{TODO_SERVICE, UPDATE_TASK, userGuid, ex});

		}
		return response;
	}

	@POST
	@Path("/")
	public TaskResponse createTask(@QueryParam("userGuid")String userGuid, CreateTaskRequest request) {
		TaskResponse response  = new TaskResponse();
		
		List<com.tradeshift.todo.webentity.Error> errors = new ArrayList<com.tradeshift.todo.webentity.Error>();
		response.setErrors(errors);
		if(StringUtils.isBlank(userGuid)){
			Error error = new Error(TaskEnum.INPUT_EXCEPTION.value(), TaskEnum.NO_USER_GUID.value(), "User Guid not present", TaskEnum.USER_GUID.value());
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid is null",new Object[]{TODO_SERVICE, CREATE_TASK});
			return response;
		}
		try{
			User user = todoHelper.getUser(userGuid);
			if(user==null){
				Error error = new Error(TaskEnum.INPUT_EXCEPTION.value(), TaskEnum.USER_NOT_FOUND.value(), "User not Found", TaskEnum.USER_GUID.value());
				errors.add(error);
				log.error("api_resource={} api_method={} userGuid={} does not exist.",new Object[]{TODO_SERVICE, CREATE_TASK, userGuid});
				return response;
			}
			validator.validate(request);
			response = todoHelper.createTask(request, user.getUserId());
		}catch(SQLException sqle){
			Error error = new Error(TaskEnum.SYSTEM_EXCEPTION.value(), sqle.getMessage(), sqle.getMessage(), "");
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid={} . SQLException occurred.",new Object[]{TODO_SERVICE, CREATE_TASK, userGuid, sqle});
		}catch(Exception ex){
			Error error = new Error(TaskEnum.SYSTEM_EXCEPTION.value(), ex.getMessage(), ex.getMessage(), "");
			errors.add(error);
			log.error("api_resource={} api_method={} userGuid={} . Exception occurred.",new Object[]{TODO_SERVICE, CREATE_TASK, userGuid, ex});

		}
		return response;
	}

}
