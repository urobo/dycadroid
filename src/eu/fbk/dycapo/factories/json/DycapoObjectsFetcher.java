/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */

public abstract class DycapoObjectsFetcher {
	private static final String TAG ="DycapoObjectsFetcher";
	

	
	/**
	 * @param code
	 * @return
	 */
	public static final String translateStatusCode(int code)
	{
		switch(code){
		case 200:
			return "Ok";
		case 201:
			return "Resource Created";
		case 204:
			return "Resource Deleted";
		case 401:
			return "Unauthorized! Invalid Credentials";
		case 403:
			return "Forbidden";
		case 404:
			return "Resource Not Found";
		case 415:
			return "Unsupported Media Type";
		}
		return null;
	}
	
	/**
	 * @param jsonArray
	 * @return
	 * @throws DycapoException
	 */
	public static final List<Person> extractPersons(JSONArray jsonArray) throws DycapoException {
		Log.d(TAG, "extractPersons");
		return PersonFetcher.fetchPersons(jsonArray);
	}

	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException 
	 */
	public static final Trip buildTrip(JSONObject responseValue) throws DycapoException{
		Log.d(TAG, "buildTrip");
		return TripFetcher.fetchTrip(responseValue);
	}
	
	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException 
	 */
	public static final Location buildLocation (JSONObject responseValue) throws DycapoException{
		Log.d(TAG, "buildLocation");
		return LocationFetcher.fetchLocation(responseValue);
	}
	
	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException 
	 */
	public static final Person buildPerson (JSONObject responseValue) throws DycapoException{
		Log.d(TAG, "buildPerson");
		return PersonFetcher.fetchPerson(responseValue);
	}
	
	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException 
	 */
	public static final Mode buildMode (JSONObject responseValue) throws DycapoException{
		Log.d(TAG, "buildMode");
		return ModeFetcher.fetchMode(responseValue);
	}
	
	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException
	 */
	public static final Participation buildParticipation(JSONObject responseValue) throws DycapoException{
		Log.d(TAG, "buildParticipation");
		return ParticipationFetcher.fetchParticipation(responseValue);
	}

}
