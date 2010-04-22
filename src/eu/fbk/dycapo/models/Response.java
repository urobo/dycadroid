/**
 * 
 */
package eu.fbk.dycapo.models;

import java.util.ArrayList;
import java.util.HashMap;

import eu.fbk.dycapo.factories.DycapoObjectsFactory;
import eu.fbk.dycapo.factories.DycapoObjectsFetcher;

/**
 * @author riccardo
 *
 */
public class Response implements DycapoObjectsFactory{
	private int code;						//must
	private String message;					//must
	private String type;					//must
	private HashMap<String,Object> value;	//must
	
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
	public HashMap<String,Object> getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(HashMap<String,Object> value) {
		this.value = value;
	}

	@Override
	public Object fetchXMLRPCResponse() {
		if (this instanceof eu.fbk.dycapo.models.Response){
			if(this.type.toLowerCase() == "trip"){
				return DycapoObjectsFetcher.buildTrip(this.value);
			}else if(this.type.toLowerCase() == "person"){
				return DycapoObjectsFetcher.buildPerson(this.value);
			}else if (this.type.toLowerCase() == "location"){
				return DycapoObjectsFetcher.buildLocation(this.value);
			}else if (this.type.toLowerCase() == "mode"){
				return DycapoObjectsFetcher.buildMode(this.value);
			}
		}
		return null;
	}

	
	
}
