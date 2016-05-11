package com.tradeshift.todo.entity;

public class Task {

	//Task Id - PK
	private Long taskId;
	
	//To Do Task message
	private String task;
	
	//Complete flag to mark the task as complete.
	private Long complete;
	
	//user who created the task
	private Long creatorId;
	
	//user to whom the task is assigned.
	private Long assigneeId;

	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Long getComplete() {
		return complete;
	}
	public void setComplete(Long complete) {
		this.complete = complete;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Long getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}
	
	
	//TODO equals and hashcode
	
	
}
