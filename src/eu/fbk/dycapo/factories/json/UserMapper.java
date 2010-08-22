/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import org.json.JSONException;
import org.json.JSONObject;

import eu.fbk.dycapo.persistency.User;

/**
 * @author riccardo
 *
 */
public abstract class UserMapper {
	public static final JSONObject fromUserToJSONObject(User usr){
		JSONObject usrTJSON = usr.toJSONObject();
		try {
			usrTJSON.put(User.PASSWORD , usr.getPassword());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return usrTJSON;
	}
}
