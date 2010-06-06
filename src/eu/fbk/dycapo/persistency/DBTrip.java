/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import android.util.Log;

import com.db4o.ObjectContainer;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */
public final class DBTrip {
	private static final String TAG = "DBTrip";
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
	
	public static void saveActiveTrip (Trip trip,boolean asd){
		 
		ActiveTrip aTrip = new ActiveTrip(trip);
		DBTrip.saveTrip(trip);
		aTrip.setActive(true);
		
		ObjectContainer db=DBProvider.getDatabase();
	
//		/final int id = aTrip.getId();
//		List <ActiveTrip> duplicate = db.query(new Predicate<ActiveTrip>() {
//		    public boolean match(ActiveTrip trip) {
//		        return trip.getId() == id;
//		    }
//		});
		
	
		db.delete(ActiveTrip.class);
		db.store(aTrip);
		db.commit();
	}
	
	public static void saveActiveTrip (ActiveTrip trip){
		Log.d(TAG, "saving ActiveTrip from trip");
		ObjectContainer db=DBProvider.getDatabase();
		db.delete(ActiveTrip.class);
		db.store(trip);
		db.commit();
	}
	
	public static ActiveTrip getActiveTrip () throws DycapoException{
		Log.d(TAG, "saving ActiveTrip from ActivteTrip");
		ObjectContainer db=DBProvider.getDatabase();
		List<ActiveTrip> all = db.queryByExample(ActiveTrip.class);
		if (all.isEmpty())throw new DycapoException("There is no active Trip");
		return all.get(0);
	}
	
}
