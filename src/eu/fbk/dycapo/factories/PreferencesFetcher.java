/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.HashMap;

import eu.fbk.dycapo.models.Preferences;

/**
 * @author riccardo
 *
 */
public class PreferencesFetcher {
	public static Preferences fetchPreferences (HashMap<String,Object> value){
		Preferences result = new Preferences();
		
		
		//FIXME WHAT THE FUCK!?
		if (value.containsKey(Preferences.AGE))result.setAge((String)value.get(Preferences.AGE));
		
		if (value.containsKey(Preferences.DRIVE))result.setDrive(((Integer)value.get(Preferences.DRIVE)==1)?Boolean.TRUE:Boolean.FALSE);
		
		if (value.containsKey(Preferences.GENDER))result.setGender(((String)value.get(Preferences.GENDER)).equals("1")?Boolean.TRUE:Boolean.FALSE);
		
		if (value.containsKey(Preferences.NONSMOKING))result.setNonsmoking(((Integer)value.get(Preferences.NONSMOKING)==1)?Boolean.TRUE:Boolean.FALSE);
		
		if (value.containsKey(Preferences.RIDE))result.setRide(((Integer)value.get(Preferences.RIDE)==1)?Boolean.TRUE:Boolean.FALSE);
		
		return result;
	}
}
