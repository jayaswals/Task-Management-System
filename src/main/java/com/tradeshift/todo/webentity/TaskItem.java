package com.tradeshift.todo.webentity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;

@XmlAccessorType(XmlAccessType.FIELD )
@XmlRootElement(name = "item")
@JsonRootName(value = "item")
public class TaskItem{

	@XmlElement(name = "id", required = true)
	private Long id;
	
	@XmlElement(name = "todoTask", required = true)
	private String todoTask;
	
	@XmlElement(name = "assigneeId", required = true)
	private String assigneeId;

	@XmlElement(name = "isComplete", required = false)
	private Long isComplete;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTodoTask() {
		return todoTask;
	}

	public void setTodoTask(String todoTask) {
		this.todoTask = todoTask;
	}

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	/**
	 * @return the isComplete
	 */
	public Long getIsComplete() {
		return isComplete;
	}

	/**
	 * @param isComplete the isComplete to set
	 */
	public void setIsComplete(Long isComplete) {
		this.isComplete = isComplete;
	}
	
	
	
}
