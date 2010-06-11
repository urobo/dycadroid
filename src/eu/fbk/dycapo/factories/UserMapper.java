/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.HashMap;

import eu.fbk.dycapo.persistency.User;

/**
 * @author riccardo
 *
 */
public final class UserMapper {
	public static final HashMap<String,Object> fromUserToHashMap(User usr){
		HashMap<String,Object> usrTHM = usr.toHashMap();
		usrTHM.put(User.PASSWORD , usr.getPassword());
		return usrTHM;
	}
}
