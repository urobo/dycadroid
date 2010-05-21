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
	
		ObjectSet<Preferences> result = db.queryByExample(Preferences.class);
		Preferences p_prefs;
		if(!result.isEmpty()){
			p_prefs= result.next();
			db.delete(Preferences.class);
			}
		else p_prefs = new Preferences();
			p_prefs.setNonsmoking(prefs.getNonsmoking());
			p_prefs.setPet(prefs.getPet());
			p_prefs.setGender(prefs.getGender());			
			db.store(p_prefs);
			db.commit();
			return true;
		
		
		
	}
	public static Preferences getPrefs(){
		ObjectContainer db=DBProvider.getDatabase();
		
		ObjectSet<Preferences> result = db.queryByExample(Preferences.class);
		if(result.hasNext()){
			Preferences prefs = result.next();
			return prefs;
			}
		return null;
	}

}
