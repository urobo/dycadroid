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

public final class DycapoObjectsFetcher {
	private static final String TAG ="DycapoObjectsFetcher";
	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.factories.DycapoObjectsFactory#fetchXMLRPCResponse(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public static Response fetchXMLRPCResponse(Object value) throws DycapoException {
			Response response = new Response();
			Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "is instance of hashmap");
			
			if(((HashMap)value).containsKey(Response.CODE))
				response.setCode((Integer)((HashMap)value).get(Response.CODE));
			
			if(((HashMap)value).containsKey(Response.MESSAGE))
				response.setMessage((String)((HashMap)value).get(Response.MESSAGE));
			
			if(((HashMap)value).containsKey(Response.TYPE)){
				response.setType((String)((HashMap)value).get(Response.TYPE));
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
						response.setValue(((Boolean)responseValue));	
				
					}else if(type.equals(Response.TYPES[1])){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES +"."+Tag.DYCAPOLOCATION, "type.equals(Response.TYPES[1]) " + Response.TYPES[1]);
						response.setValue(DycapoObjectsFetcher.buildLocation((HashMap<String,Object>)responseValue));
					
					}else if(type.equals(Response.TYPES[2])){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES +"."+Tag.DYCAPOMODE, "type.equals(Response.TYPES[2]) " + Response.TYPES[2]);
						response.setValue(DycapoObjectsFetcher.buildMode((HashMap<String,Object>)responseValue));
					
					}else if(type.equals(Response.TYPES[3])){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES +"."+Tag.DYCAPOPERSON, "type.equals(Response.TYPES[3]) " + Response.TYPES[3]);
						response.setValue(DycapoObjectsFetcher.buildPerson((HashMap<String,Object>)responseValue));
					
					}else if(type.equals(Response.TYPES[4])){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES +"."+Tag.DYCAPOTRIP, "type.equals(Response.TYPES[4]) " + Response.TYPES[4]);
						response.setValue(DycapoObjectsFetcher.buildTrip((HashMap<String,Object>)responseValue));
					}
				}
			}
		
		return response;
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

	
	public static void logResponse(Response response){
		Log.d(TAG, "Response status code : " + String.valueOf(response.getCode()));
		Log.d(TAG, "Response message : " + ((response.getMessage() instanceof String)?response.getMessage():"No Message Provided!"));
		Log.d(TAG, "Response of Type : " + ((response.getType() instanceof String)?response.getType(): "No Type Provided!" ));
	}
}
