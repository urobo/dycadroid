/**
 * 
 */
package eu.fbk.dycapo.persistency;

import com.db4o.ObjectContainer;

import eu.fbk.dycapo.models.Mode;

/**
 * @author riccardo
 *
 */
public final class DBMode {
	public static boolean saveMode(Mode car){
		ObjectContainer db= DBProvider.getDatabase();
		db.store(car);
		db.commit();
		return true;
	}
}	
