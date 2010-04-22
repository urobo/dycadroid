/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.HashMap;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Response;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */

public class DycapoObjectsFetcher implements DycapoObjectsFactory{
	
	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.factories.DycapoObjectsFactory#fetchXMLRPCResponse(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object fetchXMLRPCResponse(Object value) throws DycapoException {
		if (value instanceof HashMap<?,?>){
			if(((HashMap)value).containsKey(Response.TYPE)){
				if(((HashMap<String,Object>)value).containsKey(Response.VALUE)){
					String type = (String)((HashMap<String,Object>)value).get(Response.TYPE);
					Object responseValue=((HashMap<String,Object>)value).get(Response.VALUE);
					if (type.equals(Response.TYPES[0])){
						return new Boolean(Boolean.parseBoolean((String)responseValue));
					}else if(type.equals(Response.TYPES[1])){
						return DycapoObjectsFetcher.buildLocation((HashMap<String,Object>)responseValue);
					}else if(type.equals(Response.TYPES[2])){
						return DycapoObjectsFetcher.buildMode((HashMap<String,Object>)responseValue);
					}else if(type.equals(Response.TYPES[3])){
						return DycapoObjectsFetcher.buildPerson((HashMap<String,Object>)responseValue);
					}else if(type.equals(Response.TYPES[4])){
						return DycapoObjectsFetcher.buildTrip((HashMap<String,Object>)responseValue);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @param value
	 * @return
	 */
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
	 * @throws DycapoException 
	 */
	public static Mode buildMode (HashMap<String,Object> value) throws DycapoException{
		return ModeFetcher.fetchMode(value);
	}


}
