package com.tradeshift.todo.webentity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonRootName;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement(name = "task")
@JsonRootName(value = "task")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaskRequest {
	
	private Long isComplete;
	
	private Long taskId;
	
	private Long assigneeId;

	public Long getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Long isComplete) {
		this.isComplete = isComplete;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}
	
	

}
