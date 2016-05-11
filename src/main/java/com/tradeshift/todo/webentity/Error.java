package com.tradeshift.todo.webentity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "type", "code", "message", "parameter" })
public class Error {

	@XmlElement(name = "errorType", required = true)
	private String type;
	@XmlElement(name = "errorCode", required = true)
	private String code;
	@XmlElement(name = "errorMessage")
	private String message;
	@XmlElement(name = "errorParam")
	private String parameter;
	

	public Error() {
		super();
	}

	public Error(String type, String code, String message,
			String parameter) {
		super();
		this.type = type;
		this.code = code;
		this.message = message;
		this.parameter = parameter;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	
}