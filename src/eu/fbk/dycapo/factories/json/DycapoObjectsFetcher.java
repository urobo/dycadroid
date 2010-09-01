/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	public static final Response fetchJSONResponse(Object value) throws DycapoException {
			Response response = new Response();
			JSONObject jsonValue = (JSONObject)value;
			try {
				if(jsonValue.has(Response.CODE))				
				response.setCode(jsonValue.getInt(Response.CODE));
									
				if(jsonValue.has(Response.TYPE)){
					response.setType(jsonValue.getString(Response.TYPE));
					Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "contains type");
					
					if(jsonValue.has(Response.VALUE)){
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "contains value");
				
						String type = jsonValue.getString(Response.TYPE);
						type = type.toLowerCase();
						Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "type is :" + type);
						
						JSONObject responseValue=jsonValue.getJSONObject(Response.VALUE);
					
						if (type.equals(Response.resolveType(Response.BOOLEAN))){
							Log.d(Tag.LOG +"."+Tag.DYCAPOFACTORIES, "type.equals(Response.TYPES[0]) " + Response.TYPES[0]);
							response.setValue(jsonValue.getBoolean(Response.VALUE));	
				
						}else if(type.equals(Response.resolveType(Response.LOCATION))){
							Log.d(Tag.LOG +"."+TAG +"."+Location.TAG, "type.equals(Response.TYPES[1]) == " + Response.resolveType(Response.LOCATION));
							response.setValue(DycapoObjectsFetcher.buildLocation(responseValue));
					
						}else if(type.equals(Response.resolveType(Response.MODE))){
							Log.d(Tag.LOG +"."+TAG +"."+Mode.TAG, "type.equals(Response.TYPES[2]) == "+ Response.resolveType(Response.MODE));
							response.setValue(DycapoObjectsFetcher.buildMode(responseValue));
					
						}else if(type.equals(Response.resolveType(Response.PERSON))){
							Log.d(Tag.LOG +"."+TAG+"."+Person.TAG, "type.equals(+ Response.TYPES[3]) == " + Response.resolveType(Response.PERSON));
							response.setValue(DycapoObjectsFetcher.buildPerson(responseValue));
					
						}else if(type.equals(Response.resolveType(Response.TRIP))){
							Log.d(Tag.LOG +"."+TAG+"."+Trip.TAG, "type.equals(Response.TYPES[4]) == " + Response.resolveType(Response.TRIP));
							response.setValue(DycapoObjectsFetcher.buildTrip(responseValue));
						
						}else if (type.equals(Response.resolveType(Response.PERSONS))){
							Log.d(Tag.LOG +"+"+TAG+"."+Response.resolveType(Response.PERSONS), "type.equals(Response.TYPES[5]) == "+Response.resolveType(Response.PERSONS));
							response.setValue(DycapoObjectsFetcher.extractPersons(jsonValue.getJSONArray(Response.VALUE)));
						
						}else if (type.equals(Response.resolveType(Response.MESSAGE))){
							Log.d(Tag.LOG + "+" +TAG+"."+Response.resolveType(Response.MESSAGE),"type.equals(Response.TYPES[6]) == "+Response.resolveType(Response.MESSAGE));
							JSONObject errorMap = responseValue;
							if (errorMap.length()>0){
								JSONArray names = errorMap.names();
								String errorMsg = "";
								for(int i = 0 ; i< names.length(); i++){
									errorMsg =names.getString(i) +" "+ errorMap.getString(names.getString(i));
								}
								throw new DycapoException (errorMsg);
							}
						}
					}
				}
			return response;
		} catch (JSONException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param jsonArray
	 * @return
	 * @throws DycapoException
	 */
	private static final Person[] extractPersons(JSONArray jsonArray) throws DycapoException {
		return PersonFetcher.fetchPersons(jsonArray);
	}

	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException 
	 */
	public static final Trip buildTrip(JSONObject responseValue) throws DycapoException{
		return TripFetcher.fetchTrip(responseValue);
	}
	
	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException 
	 */
	public static final Location buildLocation (JSONObject responseValue) throws DycapoException{
		return LocationFetcher.fetchLocation(responseValue);
	}
	
	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException 
	 */
	public static final Person buildPerson (JSONObject responseValue) throws DycapoException{
		return PersonFetcher.fetchPerson(responseValue);
	}
	
	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException 
	 */
	public static final Mode buildMode (JSONObject responseValue) throws DycapoException{
		return ModeFetcher.fetchMode(responseValue);
	}

	
	public static final void logResponse(Response response){
		Log.d(TAG, "Response status code : " + String.valueOf(response.getCode()));
		Log.d(TAG, "Response message : " + ((response.getValue() instanceof String)?(String)response.getValue():"No Message Provided!"));
		Log.d(TAG, "Response of Type : " + ((response.getType() instanceof String)?response.getType(): "No Type Provided!" ));
	}
}
