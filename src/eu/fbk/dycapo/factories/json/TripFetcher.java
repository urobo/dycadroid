/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.util.Log;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.exceptions.Tag;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */
public abstract class TripFetcher {
	private static final String TAG ="TripFetcher";
	@SuppressWarnings("unchecked")
	public static final Trip fetchTrip(HashMap<String,Object> value) throws DycapoException{
			
			SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			
			Trip result= new Trip();
			String message = "error TripFetcher.fetchTrip : not enough parameters are given to define a Trip: missing ";
			
			if (value.containsKey(Trip.ID))
				result.setId((Integer)value.get(Trip.ID));
			else throw new DycapoException (message + Trip.ID);
			
			if(value.containsKey(Trip.EXPIRES))
				try {
					result.setExpires(parser.parse((String)value.get(Trip.EXPIRES)));
				} catch (ParseException e) {
					Log.e(TAG, e.getMessage());
					throw new DycapoException(e.getMessage());
				}
			else throw new DycapoException (message + Trip.EXPIRES);
			
			if(value.containsKey(Trip.PUBLISHED))
//				try {
					//result.setPublished(parser.parse((String)value.get(Trip.PUBLISHED)));
					result.setPublished((Date)value.get(Trip.PUBLISHED));
//				} catch (ParseException e) {
//					Log.e(TAG, e.getMessage());
//					throw new DycapoException(e.getMessage());
//				}
				
			if(value.containsKey(Trip.UPDATED))
//				try {
//					result.setUpdated(parser.parse((String)value.get(Trip.UPDATED)));
					result.setUpdated((Date)value.get(Trip.UPDATED));
//				} catch (ParseException e) {
//					Log.e(TAG, e.getMessage());
//					throw new DycapoException(e.getMessage());
//				}
			
			if(value.containsKey(Trip.AUTHOR))
				result.setAuthor(PersonFetcher.fetchPerson((HashMap<String,Object>)value.get(Trip.AUTHOR)));
			else throw new DycapoException (message + Trip.AUTHOR);
			
			if(value.containsKey(Trip.MODE)){
				Log.d(Tag.DYCAPOFACTORIES +"."+ Tag.DYCAPOMODE, Tag.DYCAPOMODE + " present");
				result.setMode(ModeFetcher.fetchMode((HashMap<String,Object>) value.get(Trip.MODE)));
				Log.d(Tag.DYCAPOFACTORIES +"."+ Tag.DYCAPOMODE, Tag.DYCAPOMODE + " fetched");
			}else throw new DycapoException(message + Trip.MODE);
			
			
			if (value.containsKey(Trip.PREFERENCES)){
				Log.d(Tag.DYCAPOFACTORIES +"."+ Tag.DYCAPOPREFERENCES, Tag.DYCAPOPREFERENCES + " present");
				result.setPreferences(PreferencesFetcher.fetchPreferences((HashMap<String,Object>)value.get(Trip.PREFERENCES)));
				Log.d(Tag.DYCAPOFACTORIES +"."+ Tag.DYCAPOPREFERENCES, Tag.DYCAPOPREFERENCES + " fetched");
			}else throw new DycapoException(message + Trip.PREFERENCES);
			
			if (value.containsKey(Trip.LOCATIONS)){				
				Object[] rawlocs= (Object[])value.get(Trip.LOCATIONS);
				
				int length= rawlocs.length;
				
				ArrayList<Location> waypoints = new ArrayList<Location>();
				
				for (int i = 0; i < length;i++)
				 {
					Log.d(TAG, "starting fetching location " + String.valueOf(i) + " of " + String.valueOf(length) + " locations");
					Location res = LocationFetcher.fetchLocation((HashMap<String,Object>)(rawlocs[i]));
					Log.d(TAG, "ending fetching location " + String.valueOf(i) + " of " + String.valueOf(length) + " locations");
					
					if (res.getPoint()==Location.ORIG){
						result.setOrigin(res);
						Log.d(TAG, "Origin found");
					}
					else if (res.getPoint()==Location.DEST){
						result.setDestination(res);
						Log.d(TAG, "Destionation found");
					}
					else waypoints.add(res);
				 }
				if (! waypoints.isEmpty())result.setWaypoints(waypoints);
				if (! (result.getOrigin() instanceof Location)) throw new DycapoException(message + Location.POINT_TYPE[0]);
				if (! (result.getDestination() instanceof Location)) throw new DycapoException(message + Location.POINT_TYPE[1]);
			}	
			
			return result;	
	}
}
