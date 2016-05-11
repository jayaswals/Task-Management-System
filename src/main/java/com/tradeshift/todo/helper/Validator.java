package com.tradeshift.todo.helper;

import org.springframework.stereotype.Component;

import com.tradeshift.todo.webentity.CreateTaskRequest;
import com.tradeshift.todo.webentity.UpdateTaskRequest;

@Component("validator")
public class Validator  {

	
	public void validate(CreateTaskRequest request) throws Exception{
		
		if(request==null || request.getTodoTask()==null){
			throw new Exception("EMPTY REQUEST");
		}
	}
	
	public void validate(UpdateTaskRequest request) throws Exception{
		
		if(request==null){
			throw new Exception("EMPTY REQUEST");
		}
		
		if(request.getTaskId()==null){
			throw new Exception("NO TASK ID PROVIDED");
		}
		
		if(null==request.getAssigneeId() && null==request.getIsComplete()){
			throw new Exception("NOTHING TO UPDATE");
		}
	}
}
