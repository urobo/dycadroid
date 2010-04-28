/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.Date;
import java.util.HashMap;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */
public class TripFetcher {
	@SuppressWarnings("unchecked")
	public static Trip fetchTrip(HashMap<String,Object> value) throws DycapoException{
		if (value instanceof HashMap<?,?>){
			Trip result= new Trip();
			String message = "error TripFetcher.fetchTrip : not enough parameters are given to define a Trip: missing ";
			if(value.containsKey(Trip.EXPIRES))
				result.setExpires(new Date((String)value.get(Trip.EXPIRES)));
			else throw new DycapoException (message + Trip.EXPIRES);
			
			if(value.containsKey(Trip.PUBLISHED))	
				result.setPublished(new Date((String)value.get(Trip.PUBLISHED)));
			if(value.containsKey(Trip.UPDATED))	
				result.setUpdated(new Date((String)value.get(Trip.UPDATED)));
			
			if(value.containsKey(Trip.AUTHOR))
				result.setAuthor(PersonFetcher.fetchPerson((HashMap<String,Object>)value.get(Trip.AUTHOR)));
			else throw new DycapoException (message + Trip.AUTHOR);
			
			if(value.containsKey(Trip.CONTENT))
				TripFetcher.fetchContent((HashMap<String,Object>)(value.get(Trip.CONTENT)),result);
			else throw new DycapoException(message + Trip.CONTENT);
		}	
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Trip.Content fetchContent(HashMap<String,Object> value,Trip result) throws DycapoException{
		String message = "error TripFetcher.fetchContent : not enough parameters are given to define a Trip.Content: missing ";
		if(value instanceof HashMap<?,?>){
			if(value.containsKey(Trip.Content.MODE))
				result.setMode(ModeFetcher.fetchMode((HashMap<String,Object>) value.get(Trip.Content.MODE)));
			else throw new DycapoException(message+ Trip.Content.MODE);
			
			if (value.containsKey(Trip.Content.PREFERENCES))
				result.setPreferences(PreferencesFetcher.fetchPreferences((HashMap<String,Object>)value.get(Trip.Content.PREFERENCES)));
			else throw new DycapoException(message+ Trip.Content.PREFERENCES);
			
			//if (value.containsKey(Trip.Content.LOCATIONS))
				
				//TODO 
				//value.containsKey(key);				
		}
		
		return null;
	}
}
