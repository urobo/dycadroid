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

	public static void saveTrip(Trip trip) throws DycapoException {
		ObjectContainer db = DBProvider.getDatabase();
		Trip checkDuplicate = new Trip();
		if (trip.getHref() instanceof String) {
			checkDuplicate.setHref(trip.getHref());
			List<Trip> duplicate = db.queryByExample(checkDuplicate);
			if (duplicate.isEmpty())
				db.store(trip);
			else {
				for (int i = 0; i < duplicate.size(); i++)
					db.delete(duplicate.get(i));
				db.store(trip);
			}
			checkDuplicate = null;
		} else
			throw new DycapoException("Trip doesn't contain any Id");

	}

	public static void saveActiveTripFromTrip(Trip trip) {
		Log.d(TAG, "saving ActiveTrip from Trip");
		ActiveTrip aTrip = new ActiveTrip(trip);
		aTrip.setActive(true);
		ObjectContainer db = DBProvider.getDatabase();
		db.delete(ActiveTrip.class);
		db.store(aTrip);
		db.commit();
		Log.d(TAG, "Trip Saved!");
	}

	public static void saveActiveTrip(ActiveTrip trip) {
		Log.d(TAG, "saving ActiveTrip from ActiveTrip");
		ObjectContainer db = DBProvider.getDatabase();
		db.delete(ActiveTrip.class);
		db.store(trip);
		db.commit();
	}

	public static ActiveTrip getActiveTrip() throws DycapoException {
		Log.d(TAG, "saving ActiveTrip from ActivteTrip");
		ObjectContainer db = DBProvider.getDatabase();
		List<ActiveTrip> all = db.queryByExample(ActiveTrip.class);
		if (all.isEmpty())
			throw new DycapoException("There is no active Trip");
		return all.get(0);
	}

}
