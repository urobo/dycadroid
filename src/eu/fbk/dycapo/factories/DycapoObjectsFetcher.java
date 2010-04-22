/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.Date;
import java.util.HashMap;

import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */

public class DycapoObjectsFetcher {
	
	/**
	 * @param value
	 * @return
	 */
//	@SuppressWarnings("unchecked")
	public static Trip buildTrip(HashMap<String,Object> value){
		return TripFetcher.fetchTrip(value);
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static Location buildLocation (HashMap<String,Object> value){
		return LocationFetcher.fetchLocation(value);
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static Person buildPerson (HashMap<String,Object> value){
		return PersonFetcher.fetchPerson(value);
	}
	
	/**
	 * @param value
	 * @return
	 */
	public static Mode buildMode (HashMap<String,Object> value){
		return ModeFetcher.fetchMode(value);
	}
}
