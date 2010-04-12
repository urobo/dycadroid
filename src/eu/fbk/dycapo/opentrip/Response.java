/**
 * 
 */
package eu.fbk.dycapo.opentrip;

import java.util.HashMap;

/**
 * @author riccardo
 *
 */
public class Response {
	private int code;
	private String message;
	private String type;
	private HashMap value;
	
	/**
	 * 
	 */
	public Response() {
		
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
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
	 * @return the value
	 */
	public HashMap getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(HashMap value) {
		this.value = value;
	}
}
