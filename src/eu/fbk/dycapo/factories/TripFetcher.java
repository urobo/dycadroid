/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.Date;
import java.util.HashMap;

import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */
public class TripFetcher {
	public static Trip fetchTrip(HashMap<String,Object> value){
Trip result= new Trip();
		
//		Object param = (Object)value.get(Trip.AUTHOR);
//		if (param instanceof HashMap<?,?>){
//			result.setAuthor(DycapoObjectsFetcher.buildPerson((HashMap<String,Object>)param));
//		}
//		
//		param = (Object)value.get(Trip.CONTENT);
//		if (param instanceof HashMap<?,?>){
//			HashMap<String,Object>asd=(HashMap<String, Object>) ((HashMap) param).entrySet();
//			Object dest= (Object)asd.get(Trip.Content.DESTINATION);
//			
//		}
		
		result.setExpires(new Date((String)value.get(Trip.EXPIRES)));
		result.setPublished(new Date((String)value.get(Trip.PUBLISHED)));
		result.setUpdated(new Date((String)value.get(Trip.UPDATED)));
		
			
		return null;
	}
}
