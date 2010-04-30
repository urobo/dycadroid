/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.HashMap;

import android.util.Log;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.exceptions.Tag;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Response;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */

public class DycapoObjectsFetcher {
	
	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.factories.DycapoObjectsFactory#fetchXMLRPCResponse(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public static Object fetchXMLRPCResponse(Object value) throws DycapoException {
			
			Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "is instance of hashmap");
			if(((HashMap)value).containsKey(Response.TYPE)){
				Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "contains type");
				if(((HashMap<String,Object>)value).containsKey(Response.VALUE)){
					Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "contains value");
				
					String type = (String)((HashMap<String,Object>)value).get(Response.TYPE);
					type = type.toLowerCase();
					Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "type is :" + type);
					Object responseValue=((HashMap<String,Object>)value).get(Response.VALUE);
					if (type.equals(Response.TYPES[0])){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "type.equals(Response.TYPES[0]) " + Response.TYPES[0]);
						if(!((Boolean)responseValue))
							Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES,"DyCaPo.status_code : " +(String)((HashMap<String,Object>)value).get(Response.CODE).toString() + " DyCaPo.message : " +((HashMap<String,Object>)value).get(Response.MESSAGE));
						return ((Boolean)responseValue);	
					}else if(type.equals(Response.TYPES[1])){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES +"."+Tag.DYCAPOLOCATION, "type.equals(Response.TYPES[1]) " + Response.TYPES[1]);
						return DycapoObjectsFetcher.buildLocation((HashMap<String,Object>)responseValue);
					
					}else if(type.equals(Response.TYPES[2])){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES +"."+Tag.DYCAPOMODE, "type.equals(Response.TYPES[2]) " + Response.TYPES[2]);
						return DycapoObjectsFetcher.buildMode((HashMap<String,Object>)responseValue);
					}else if(type.equals(Response.TYPES[3])){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES +"."+Tag.DYCAPOPERSON, "type.equals(Response.TYPES[3]) " + Response.TYPES[3]);
						return DycapoObjectsFetcher.buildPerson((HashMap<String,Object>)responseValue);
					}else if(type.equals(Response.TYPES[4])){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES +"."+Tag.DYCAPOTRIP, "type.equals(Response.TYPES[4]) " + Response.TYPES[4]);
						return DycapoObjectsFetcher.buildTrip((HashMap<String,Object>)responseValue);
					}
				}
			}
		
		return null;
	}
	
	/**
	 * @param value
	 * @return
	 * @throws DycapoException 
	 */
	public static Trip buildTrip(HashMap<String,Object> value) throws DycapoException{
		return TripFetcher.fetchTrip(value);
	}
	
	/**
	 * @param value
	 * @return
	 * @throws DycapoException 
	 */
	public static Location buildLocation (HashMap<String,Object> value) throws DycapoException{
		return LocationFetcher.fetchLocation(value);
	}
	
	/**
	 * @param value
	 * @return
	 * @throws DycapoException 
	 */
	public static Person buildPerson (HashMap<String,Object> value) throws DycapoException{
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
