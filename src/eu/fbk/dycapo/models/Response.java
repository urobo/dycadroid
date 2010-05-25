/**
 * 
 */
package eu.fbk.dycapo.models;


/**
 * @author riccardo
 *
 */
public class Response{
	
	public static final String CODE = "code";
	public static final String MESSAGE = "message";
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String[] TYPES = {  "boolean",
											"location",
											"mode",
											"person",
											"trip",
											};
	
	private Integer code;						//must
	private String message;					//must
	private String type;					//must
	private Object value;	//must
	
	/**
	 * 
	 */
	public Response() {
		
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
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
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
}
