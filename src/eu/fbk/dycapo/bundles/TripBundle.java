/**
 * 
 */
package eu.fbk.dycapo.bundles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.util.Log;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */
public abstract class TripBundle {
	public static final String TAG = "TripBundle";
	
	public static final Bundle toBundle (Trip trip){
		Bundle result = new Bundle();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		if (trip.getAuthor() instanceof Person)
			result.putBundle(Trip.AUTHOR, PersonBundle.toBundle(trip.getAuthor()));
		
		Bundle locations = new Bundle();
		
		if (trip.getOrigin() instanceof Location)
			locations.putBundle(Location.POINT_TYPE[Location.ORIG], LocationBundle.toBundle(trip.getOrigin()));
		
		if (trip.getDestination() instanceof Location)
			locations.putBundle(Location.POINT_TYPE[Location.DEST], LocationBundle.toBundle(trip.getDestination()));
		
		int size = trip.getWaypoints().size();
		for (int i = 0 ; i< size; i++)
			locations.putBundle(Location.POINT_TYPE[Location.WAYP]+ String.valueOf(i), LocationBundle.toBundle(trip.getWaypoints().get(i)));
		
		if(!locations.isEmpty())
			result.putBundle(Trip.LOCATIONS, locations);
		
		if (result.containsKey(Trip.MODE))
			result.putBundle(Trip.MODE, ModeBundle.toBundle(trip.getMode()));
		
		if (result.containsKey(Trip.PREFERENCES))
				result.putBundle(Trip.PREFERENCES, PrefsBundle.toBundle(trip.getPreferences()));
		
		if (trip.getExpires() instanceof Date)
			result.putString(Trip.EXPIRES, sdf.format(trip.getExpires()));
		
		if (trip.getHref() instanceof String)
			result.putString(Trip.HREF, trip.getHref());
		
		if (trip.getPublished() instanceof Date)
			result.putString(Trip.PUBLISHED, sdf.format(trip.getPublished()));
		
		if (trip.getUpdated() instanceof Date)
			result.putString(Trip.UPDATED, sdf.format(trip.getUpdated()));
			
		return result;
	}
	
	public static final Trip fromBundle (Bundle data){
		
		Trip trip = new Trip();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		if (data.containsKey(Trip.AUTHOR))
			trip.setAuthor(PersonBundle.fromBundle(data.getBundle(Trip.AUTHOR)));
		
		if (data.containsKey(Trip.LOCATIONS)){
			Bundle locations = data.getBundle(Trip.LOCATIONS);
			
			if (locations.containsKey(Location.POINT_TYPE[Location.ORIG]))
				trip.setOrigin(LocationBundle.fromBundle((locations.getBundle(Location.POINT_TYPE[Location.ORIG]))));
		
			if (locations.containsKey(Location.POINT_TYPE[Location.DEST]))
				trip.setDestination(LocationBundle.fromBundle(locations.getBundle(Location.POINT_TYPE[Location.DEST])));
			
			int i = 0;
			while (locations.containsKey(Location.POINT_TYPE[Location.WAYP] + String.valueOf(i))){
				Location wayp  = new Location();
				wayp = LocationBundle.fromBundle(locations.getBundle(Location.POINT_TYPE[Location.WAYP] + String.valueOf(i)));
				trip.getWaypoints().add(wayp);
			}
		}				
			if (data.containsKey(Trip.PREFERENCES))
				trip.setPreferences(PrefsBundle.fromBundle(data.getBundle(Trip.PREFERENCES)));
			
			if (data.containsKey(Trip.MODE))
				trip.setMode(ModeBundle.fromBundle(data.getBundle(Trip.MODE)));
		
		if (data.containsKey(Trip.EXPIRES)){	
			try {
				trip.setExpires(sdf.parse(data.getString(Trip.EXPIRES)));
			} catch (ParseException e) {
				Log.e(TAG, e.getMessage());
			}
		}
		
		if (data.containsKey(Trip.HREF))
				trip.setHref(data.getString(Trip.HREF));
		
		if (data.containsKey(Trip.PUBLISHED)){
			try {
				trip.setPublished(sdf.parse(data.getString(Trip.PUBLISHED)));
			} catch (ParseException e) {
				Log.e(TAG, e.getMessage());
			}
		}
		
		if (data.containsKey(Trip.UPDATED)){
			try {
				trip.setUpdated(sdf.parse(data.getString(Trip.UPDATED)));
			} catch (ParseException e) {
				Log.e(TAG, e.getMessage());
			}
		}
		return trip;
	}
}
