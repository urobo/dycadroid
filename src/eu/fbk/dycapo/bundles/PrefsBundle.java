/**
 * 
 */
package eu.fbk.dycapo.bundles;

import android.os.Bundle;
import eu.fbk.dycapo.models.Preferences;

/**
 * @author riccardo
 *
 */
public abstract class PrefsBundle {
	public static final Bundle toBundle (Preferences prefs){
		Bundle result = new Bundle();
		
		if (prefs.getAge() instanceof String)
			result.putString(Preferences.AGE, prefs.getAge());
		
		if (prefs.getDrive() instanceof Boolean)
			result.putBoolean(Preferences.DRIVE, prefs.getDrive());
		
		if (prefs.getGender() instanceof Integer)
			result.putInt(Preferences.GENDER, prefs.getGender());
		
		if (prefs.getNonsmoking() instanceof Boolean)
			result.putBoolean(Preferences.NONSMOKING, prefs.getNonsmoking());
		
		if (prefs.getPet() instanceof Boolean)
			result.putBoolean(Preferences.PET, prefs.getPet());
		
		if (prefs.getRide() instanceof Boolean)
			result.putBoolean(Preferences.RIDE, prefs.getRide());
		
		return result;
	}
	
	public static final Preferences fromBundle (Bundle data){
		Preferences prefs = new Preferences ();
		
		if (data.containsKey(Preferences.AGE))
			prefs.setAge(data.getString(Preferences.AGE));
		
		if (data.containsKey(Preferences.DRIVE))
			prefs.setDrive(data.getBoolean(Preferences.DRIVE));
		
		if (data.containsKey(Preferences.GENDER))
			prefs.setGender(data.getInt(Preferences.GENDER));
		
		if (data.containsKey(Preferences.NONSMOKING))
			prefs.setNonsmoking(data.getBoolean(Preferences.NONSMOKING));
		
		if (data.containsKey(Preferences.PET))
			prefs.setPet(data.getBoolean(Preferences.PET));
		
		if (data.containsKey(Preferences.RIDE))
			prefs.setRide(data.getBoolean(Preferences.RIDE));
		
		return prefs;
	}
}
