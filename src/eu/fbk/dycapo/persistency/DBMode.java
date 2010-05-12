/**
 * 
 */
package eu.fbk.dycapo.persistency;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import eu.fbk.dycapo.models.Mode;

/**
 * @author riccardo
 *
 */
public final class DBMode {
	public static boolean saveMode(Mode car){
		ObjectContainer db= DBProvider.getDatabase();
		ObjectSet<User> found = db.queryByExample(User.class);
		if (!found.isEmpty()){
			User user=found.next();
			user.setCar(car);
			db.delete(User.class);
			db.store(user);
			db.commit();
		}
		return false;
	}
}	
