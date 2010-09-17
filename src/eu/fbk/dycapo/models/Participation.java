/**
 * 
 */
package eu.fbk.dycapo.models;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import eu.fbk.dycapo.factories.json.DycapoJSONObjects;

/**
 * @author riccardo
 *
 */
public class Participation implements DycapoJSONObjects{
	private static final String TAG = "Participation";
	public static final String PERSON = "person";
	public static final String STATUS = "status";
	
	protected static final String REQUEST = "request";
	protected static final String ACCEPT = "accept";
	protected static final String START = "start";
	protected static final String FINISH = "finish";
	
	private Person person;
	private String status;
	protected String href;
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}
	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}
	
	@Override
	public JSONObject toJSONObject() {
		JSONObject result = new JSONObject();
		try {
			if (this.person instanceof Person)
				result.put(PERSON, this.person.toJSONObject());
			if (this.status instanceof String)
				result.put(STATUS, this.status);
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		Log.d(TAG,result.toString());
		return result;
	}
	
}
