/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import com.db4o.ObjectContainer;

import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */
public final class DBTrip {
	public static void saveTrip(Trip trip){
		ObjectContainer db=DBProvider.getDatabase();
		Trip checkDuplicate= new Trip();
		checkDuplicate.setId(trip.getId());
		List<Trip> duplicate = db.queryByExample(checkDuplicate);
		if (duplicate.isEmpty())db.store(trip);
		else {
			for (int i=0; i < duplicate.size();i++)
				db.delete(duplicate.get(i));
			db.store(trip);
		}
		checkDuplicate=null;
	}
	
}
