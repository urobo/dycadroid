/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import eu.fbk.dycapo.models.Preferences;

/**
 * @author riccardo
 *
 */
public final class DBPrefs {
	
	public static boolean findPrefs(Preferences prefs){
		ObjectContainer db=DBProvider.getDatabase();
		List<Preferences> already= db.queryByExample(prefs);
		if (already.isEmpty())return true;
		return false;
	}
	
	public static boolean deletePrefs(){
		try{
			ObjectContainer db=DBProvider.getDatabase();
			ObjectSet<Preferences> delete = db.queryByExample(Preferences.class);
			db.delete(delete);
			db.commit();
			return true;
		}catch (Exception e){
			return false;
		}
	}
	
	public static boolean savePrefs(Preferences prefs){
		
		ObjectContainer db=DBProvider.getDatabase();
	
		ObjectSet<User> result = db.queryByExample(User.class);
		if(!result.isEmpty()){
			User user= result.next();
			user.setPrefs(prefs);
			db.delete(User.class);
			db.store(user);
			db.commit();
			return true;
		}
		
		return false;
	}
	

}
