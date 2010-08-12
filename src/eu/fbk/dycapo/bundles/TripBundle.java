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
	
	private static final Bundle toContentBundle (Trip.Content content){
		Bundle result = new Bundle();
		Bundle locations = new Bundle();
		
		if (content.getOrigin() instanceof Location)
			locations.putBundle(Location.POINT_TYPE[Location.ORIG], LocationBundle.toBundle(content.getOrigin()));
		
		if (content.getDestination() instanceof Location)
			locations.putBundle(Location.POINT_TYPE[Location.DEST], LocationBundle.toBundle(content.getDestination()));
		
		int size = content.getWaypoints().size();
		for (int i = 0 ; i< size; i++)
			locations.putBundle(Location.POINT_TYPE[Location.WAYP]+ String.valueOf(i), LocationBundle.toBundle(content.getWaypoints().get(i)));
		
		if(!locations.isEmpty())
			result.putBundle(Trip.Content.LOCATIONS, locations);
		
		if (result.containsKey(Trip.Content.MODE))
			result.putBundle(Trip.Content.MODE, ModeBundle.toBundle(content.getMode()));
		
		if (result.containsKey(Trip.Content.PREFERENCES))
				result.putBundle(Trip.Content.PREFERENCES, PrefsBundle.toBundle(content.getPreferences()));
					
		return result;
	}
	
	private static final void fromContentBundle(Trip trip,Bundle data){
		Trip.Content content = trip.getContent();
		if (data.containsKey(Trip.Content.LOCATIONS)){
			Bundle locations = data.getBundle(Trip.Content.LOCATIONS);
			
			if (locations.containsKey(Location.POINT_TYPE[Location.ORIG]))
				content.setOrigin(LocationBundle.fromBundle((locations.getBundle(Location.POINT_TYPE[Location.ORIG]))));
		
			if (locations.containsKey(Location.POINT_TYPE[Location.DEST]))
				content.setDestination(LocationBundle.fromBundle(locations.getBundle(Location.POINT_TYPE[Location.DEST])));
			
			int i = 0;
			while (locations.containsKey(Location.POINT_TYPE[Location.WAYP] + String.valueOf(i))){
				Location wayp  = new Location();
				wayp = LocationBundle.fromBundle(locations.getBundle(Location.POINT_TYPE[Location.WAYP] + String.valueOf(i)));
				content.getWaypoints().add(wayp);
			}
		}				
			if (data.containsKey(Trip.Content.PREFERENCES))
				content.setPreferences(PrefsBundle.fromBundle(data.getBundle(Trip.Content.PREFERENCES)));
			
			if (data.containsKey(Trip.Content.MODE))
				content.setMode(ModeBundle.fromBundle(data.getBundle(Trip.Content.MODE)));
			
			trip.setContent(content);
	}
	
	public static final Bundle toBundle (Trip trip){
		Bundle result = new Bundle();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		if (trip.getAuthor() instanceof Person)
			result.putBundle(Trip.AUTHOR, PersonBundle.toBundle(trip.getAuthor()));
		
		if (trip.getContent() instanceof Trip.Content)
			result.putBundle(Trip.CONTENT, TripBundle.toContentBundle(trip.getContent()));
		
		if (trip.getExpires() instanceof Date)
			result.putString(Trip.EXPIRES, sdf.format(trip.getExpires()));
		
		if (trip.getId() instanceof Integer)
			result.putInt(Trip.ID, trip.getId());
		
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
		
		if (data.containsKey(Trip.CONTENT))
			TripBundle.fromContentBundle(trip, data.getBundle(Trip.CONTENT));
		
		if (data.containsKey(Trip.EXPIRES)){	
			try {
				trip.setExpires(sdf.parse(data.getString(Trip.EXPIRES)));
			} catch (ParseException e) {
				Log.e(TAG, e.getMessage());
			}
		}
		
		if (data.containsKey(Trip.ID))
			trip.setId(data.getInt(Trip.ID));
		
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
