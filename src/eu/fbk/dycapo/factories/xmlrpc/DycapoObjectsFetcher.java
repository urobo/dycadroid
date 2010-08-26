/**
 * 
 */
package eu.fbk.dycapo.factories.xmlrpc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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


public abstract class DycapoObjectsFetcher {
	private static final String TAG ="DycapoObjectsFetcher";
	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.factories.DycapoObjectsFactory#fetchXMLRPCResponse(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public static final Response fetchXMLRPCResponse(Object value) throws DycapoException {
			Response response = new Response();
			Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "is instance of hashmap");
			
			if(((HashMap)value).containsKey(Response.CODE))
				response.setCode((Integer)((HashMap)value).get(Response.CODE));
			
					
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
						Log.d(Tag.LOG +"."+TAG +"."+Location.TAG, "type.equals(Response.TYPES[1]) == " + Response.resolveType(Response.LOCATION));
						response.setValue(DycapoObjectsFetcher.buildLocation((HashMap<String,Object>)responseValue));
					
					}else if(type.equals(Response.TYPES[2])){
						Log.d(Tag.LOG +"."+TAG +"."+Mode.TAG, "type.equals(Response.TYPES[2]) == "+ Response.resolveType(Response.MODE));
						response.setValue(DycapoObjectsFetcher.buildMode((HashMap<String,Object>)responseValue));
					
					}else if(type.equals(Response.TYPES[3])){
						Log.d(Tag.LOG +"."+TAG+"."+Person.TAG, "type.equals(+ Response.TYPES[3]) == " + Response.resolveType(Response.PERSON));
						response.setValue(DycapoObjectsFetcher.buildPerson((HashMap<String,Object>)responseValue));
					
					}else if(type.equals(Response.TYPES[4])){
						Log.d(Tag.LOG +"."+TAG+"."+Trip.TAG, "type.equals(Response.TYPES[4]) == " + Response.resolveType(Response.TRIP));
						response.setValue(DycapoObjectsFetcher.buildTrip((HashMap<String,Object>)responseValue));
					
					}else if (type.equals(Response.TYPES[5])){
						Log.d(Tag.LOG +"+"+TAG+"."+Response.resolveType(Response.PERSONS), "type.equals(Response.TYPES[5]) == "+Response.resolveType(Response.PERSONS));
						response.setValue(DycapoObjectsFetcher.extractPersons((Object[])responseValue));
					
					}else if (type.equals(Response.TYPES[6])){
						Log.d(Tag.LOG +"+"+TAG+"."+Response.resolveType(Response.PERSONS), "type.equals(Response.TYPES[5]) == "+Response.resolveType(Response.PERSONS));
						HashMap<String,String> errorMap = (HashMap<String,String>)responseValue;
						if (!errorMap.isEmpty()){
							Set<String> keys = errorMap.keySet();
							Iterator<String> i =  keys.iterator();
							String errorMsg = "";
							while(i.hasNext()){
								errorMsg = i.next() +" "+ errorMap.get(i.next());
							}
							throw new DycapoException (errorMsg);
						}
						
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
	private static final Person[] extractPersons(Object[] value) throws DycapoException {
		return PersonFetcher.fetchPersons(value);
	}

	/**
	 * @param value
	 * @return
	 * @throws DycapoException 
	 */
	public static final Trip buildTrip(HashMap<String,Object> value) throws DycapoException{
		return TripFetcher.fetchTrip(value);
	}
	
	/**
	 * @param value
	 * @return
	 * @throws DycapoException 
	 */
	public static final Location buildLocation (HashMap<String,Object> value) throws DycapoException{
		return LocationFetcher.fetchLocation(value);
	}
	
	/**
	 * @param value
	 * @return
	 * @throws DycapoException 
	 */
	public static final Person buildPerson (HashMap<String,Object> value) throws DycapoException{
		return PersonFetcher.fetchPerson(value);
	}
	
	/**
	 * @param value
	 * @return
	 * @throws DycapoException 
	 */
	public static final Mode buildMode (HashMap<String,Object> value) throws DycapoException{
		return ModeFetcher.fetchMode(value);
	}

	

}
