package com.tradeshift.todo.webentity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonRootName;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement(name = "task")
@JsonRootName(value = "task")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskRequest {

	@XmlElement(name = "todo", required = true)
	private String todoTask;

	public String getTodoTask() {
		return todoTask;
	}

	public void setTodoTask(String todoTask) {
		this.todoTask = todoTask;
	}
	
}
