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
	public static final String AUTHOR = "author";
	public static final String STATUS = "status";
	public static final String HREF = "href";
	public static final String ROLE = "role";
	
	public static final String REQUESTED = "request";
	public static final String ACCEPTED = "accept";
	public static final String STARTED = "start";
	public static final String FINISHED = "finish";
	
	private String role;
	private Person author;
	private String status;
	protected String href;
	
	/**
	 * 
	 */
	public Participation() {
	
	}
	/**
	 * @return the person
	 */
	public Person getAuthor() {
		return author;
	}
	/**
	 * @param person the person to set
	 */
	public void setAuthor(Person person) {
		this.author = person;
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
			if (this.author instanceof Person)
				result.put(AUTHOR, this.author.toJSONObject());
			if (this.status instanceof String)
				result.put(STATUS, this.status);
			if (this.role instanceof String)
				result.put(ROLE, this.role);
			
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		Log.d(TAG,result.toString());
		return result;
	}
	
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "";
		if (this.getStatus() instanceof String)
			result += "Status : "+ this.status;
		if (this.getAuthor() instanceof Person){
			result += "\n User : " + this.author.getUsername();
			result += " Age : " + this.author.getAge();
			result += " Gender : " + this.author.getGender();
			result += " Info : ";
			if (this.getAuthor().getDeaf())
				result += "D";
			if (this.getAuthor().getBlind())
				result += "B";
			if (this.getAuthor().getDog())
				result += "P";
			if (this.getAuthor().getSmoker())
				result += "S";
			if (this.role instanceof String)
				result += " role: " + this.role;
		}
		return result;
	}
}
