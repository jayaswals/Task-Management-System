package com.tradeshift.todo.helper;

public enum TaskEnum  {

	USER_NOT_FOUND("USER_NOT_FOUND"),
	USER_GUID("userGuid"),
	INPUT_EXCEPTION("INPUT EXCEPTION"),
	SYSTEM_EXCEPTION("SYSTEM EXCEPTION"),
	NO_USER_GUID ("NO_USER_GUID");
	
	private String value;
	
	private TaskEnum(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
}
