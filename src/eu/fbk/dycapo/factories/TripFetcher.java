/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */
public class TripFetcher {
	@SuppressWarnings("unchecked")
	public static Trip fetchTrip(HashMap<String,Object> value) throws DycapoException{
		
			Trip result= new Trip();
			String message = "error TripFetcher.fetchTrip : not enough parameters are given to define a Trip: missing ";
			if(value.containsKey(Trip.EXPIRES))
				result.setExpires((Date)value.get(Trip.EXPIRES));
			else throw new DycapoException (message + Trip.EXPIRES);
			
			if(value.containsKey(Trip.PUBLISHED))	
				result.setPublished((Date)value.get(Trip.PUBLISHED));
			if(value.containsKey(Trip.UPDATED))	
				result.setUpdated((Date)value.get(Trip.UPDATED));
			
			if(value.containsKey(Trip.AUTHOR))
				result.setAuthor(PersonFetcher.fetchPerson((HashMap<String,Object>)value.get(Trip.AUTHOR)));
			else throw new DycapoException (message + Trip.AUTHOR);
			
			if(value.containsKey(Trip.CONTENT))
				TripFetcher.fetchContent((HashMap<String,Object>)(value.get(Trip.CONTENT)),result);
			else throw new DycapoException(message + Trip.CONTENT);
			
			return result;	
}
	
	@SuppressWarnings("unchecked")
	public static void fetchContent(HashMap<String,Object> value,Trip result) throws DycapoException{
		String message = "error TripFetcher.fetchContent : not enough parameters are given to define a Trip.Content: missing ";
		
			if(value.containsKey(Trip.Content.MODE))
				result.setMode(ModeFetcher.fetchMode((HashMap<String,Object>) value.get(Trip.Content.MODE)));
			else throw new DycapoException(message + Trip.Content.MODE);
			
			if (value.containsKey(Trip.Content.PREFERENCES))
				result.setPreferences(PreferencesFetcher.fetchPreferences((HashMap<String,Object>)value.get(Trip.Content.PREFERENCES)));
			else throw new DycapoException(message + Trip.Content.PREFERENCES);
			
			if (value.containsKey(Trip.Content.LOCATIONS)){				
				Object[] rawlocs= (Object[])value.get(Trip.Content.LOCATIONS);
				
				int length= rawlocs.length;
				
				ArrayList<Location> waypoints = new ArrayList<Location>();
				
				for (int i = 0; i < length;i++)
				 {
					Location res = LocationFetcher.fetchLocation((HashMap<String,Object>)rawlocs[i]);
					if (res.getPoint()== 0)result.setOrigin(res);
					else if (res.getPoint()==1)result.setDestination(res);
					else waypoints.add(res);
				 }
				if(!waypoints.isEmpty())result.getContent().setWaypoints(waypoints);
				if (! (result.getOrigin() instanceof Location)) throw new DycapoException(message + Location.POINT_TYPE[0]);
				if (! (result.getDestination() instanceof Location)) throw new DycapoException(message + Location.POINT_TYPE[1]);
			}	
	}
}
