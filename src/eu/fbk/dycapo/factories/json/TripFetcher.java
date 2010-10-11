/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	public static final Trip fetchTrip(JSONObject responseValue) throws DycapoException{
		try{
			SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			
			Trip result= new Trip();
			String message = "error TripFetcher.fetchTrip : not enough parameters are given to define a Trip: missing ";
			
			if (responseValue.has(DycapoObjectsFetcher.HREF))
				result.setHref(responseValue.getString(DycapoObjectsFetcher.HREF));
			
			if (responseValue.has(Trip.ACTIVE))
				result.setActive(responseValue.getBoolean(Trip.ACTIVE));
			
			if(responseValue.has(Trip.EXPIRES))
				try {
					result.setExpires(parser.parse(responseValue.getString(Trip.EXPIRES)));
				} catch (ParseException e) {
					Log.e(TAG, e.getMessage());
					throw new DycapoException(e.getMessage());
				}
			else throw new DycapoException (message + Trip.EXPIRES);
			
			if(responseValue.has(Trip.PUBLISHED))
				try {
					result.setPublished(parser.parse(responseValue.getString(Trip.PUBLISHED)));
				} catch (ParseException e) {
					Log.e(TAG, e.getMessage());
					throw new DycapoException(e.getMessage());
				}
				
			if(responseValue.has(Trip.UPDATED))
				try {
					result.setUpdated(parser.parse(responseValue.getString(Trip.UPDATED)));
				} catch (ParseException e) {
					Log.e(TAG, e.getMessage());
					throw new DycapoException(e.getMessage());
				}
			
			if(responseValue.has(Trip.AUTHOR))
				result.setAuthor(PersonFetcher.fetchPerson(responseValue.getJSONObject(Trip.AUTHOR)));
			else throw new DycapoException (message + Trip.AUTHOR);
			
			if(responseValue.has(Trip.MODE)){
				Log.d(Tag.DYCAPOFACTORIES +"."+ Tag.DYCAPOMODE, Tag.DYCAPOMODE + " present");
				result.setMode(ModeFetcher.fetchMode(responseValue.getJSONObject(Trip.MODE)));
				Log.d(Tag.DYCAPOFACTORIES +"."+ Tag.DYCAPOMODE, Tag.DYCAPOMODE + " fetched");
			}else throw new DycapoException(message + Trip.MODE);
			
			
			if (responseValue.has(Trip.PREFERENCES)){
				Log.d(Tag.DYCAPOFACTORIES +"."+ Tag.DYCAPOPREFERENCES, Tag.DYCAPOPREFERENCES + " present");
				result.setPreferences(PreferencesFetcher.fetchPreferences(responseValue.getJSONObject(Trip.PREFERENCES)));
				Log.d(Tag.DYCAPOFACTORIES +"."+ Tag.DYCAPOPREFERENCES, Tag.DYCAPOPREFERENCES + " fetched");
			}else throw new DycapoException(message + Trip.PREFERENCES);
			
			if (responseValue.has(Trip.LOCATIONS)){				
				JSONArray rawlocs= responseValue.getJSONArray(Trip.LOCATIONS);
				
				int length= rawlocs.length();
				
				ArrayList<Location> waypoints = new ArrayList<Location>();
				
				for (int i = 0; i < length;i++)
				 {
					Log.d(TAG, "starting fetching location " + String.valueOf(i) + " of " + String.valueOf(length) + " locations");
					Location res = LocationFetcher.fetchLocation(rawlocs.getJSONObject(i));
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
		}catch(JSONException e){
			e.printStackTrace();
			
		}
		return null;
	}
	
	public static final List<Trip> extractTrips(JSONArray value) throws DycapoException{
		List<Trip> trips = new ArrayList<Trip>();
		try {
			for (int i = 0 ; i < value.length(); i++){
				trips.add(fetchTrip(value.getJSONObject(i)));
			}
			return trips;
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
