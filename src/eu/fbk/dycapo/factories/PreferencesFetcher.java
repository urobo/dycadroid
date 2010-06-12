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
//		if (value.containsKey(Preferences.AGE))result.setAge((String)value.get(Preferences.AGE));
		
		if (value.containsKey(Preferences.DRIVE))
			result.setDrive((Boolean)value.get(Preferences.DRIVE));
		
		if (value.containsKey(Preferences.GENDER)){
			int i = 0;
			while (i<Preferences.GENDER_PREFS.length){
				if (((String)value.get(Preferences.GENDER)).toLowerCase().equals(Preferences.GENDER_TO[i]))
					result.setGender(i);
				i++;
			}
		}
		
		if (value.containsKey(Preferences.NONSMOKING))
			//result.setNonsmoking(((Integer)value.get(Preferences.NONSMOKING)==1)?Boolean.TRUE:Boolean.FALSE);
			result.setNonsmoking((Boolean)value.get(Preferences.NONSMOKING));
		
		if (value.containsKey(Preferences.RIDE))
			//result.setRide(((Integer)value.get(Preferences.RIDE)==1)?Boolean.TRUE:Boolean.FALSE);
			result.setRide((Boolean)value.get(Preferences.RIDE));
		return result;
	}
}
