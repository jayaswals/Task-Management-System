package com.tradeshift.todo.webentity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "task")
@JsonRootName(value = "task")
public class TaskResponse{

	
	@XmlElement(name = "items", required = false)
	private List<TaskItem> items;
	
	@XmlElement(name = "errors", required = false)
	private List<Error> errors;

	public List<TaskItem> getItems() {
		return items;
	}

	public void setItems(List<TaskItem> items) {
		this.items = items;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	
	
	
}
