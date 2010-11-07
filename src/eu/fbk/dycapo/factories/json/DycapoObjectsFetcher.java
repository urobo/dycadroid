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
import eu.fbk.dycapo.models.Search;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 * 
 */

public abstract class DycapoObjectsFetcher {
	private static final String TAG = "DycapoObjectsFetcher";
	public static final String HREF = "href";

	/**
	 * @param jsonArray
	 * @return
	 * @throws DycapoException
	 */
	public static final List<Person> extractPersons(JSONArray jsonArray)
			throws DycapoException {
		Log.d(TAG, "extractPersons");
		return PersonFetcher.fetchPersons(jsonArray);
	}

	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException
	 */
	public static final Trip buildTrip(JSONObject responseValue)
			throws DycapoException {
		Log.d(TAG, "buildTrip");
		return TripFetcher.fetchTrip(responseValue);
	}

	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException
	 */
	public static final Location buildLocation(JSONObject responseValue)
			throws DycapoException {
		Log.d(TAG, "buildLocation");
		return LocationFetcher.fetchLocation(responseValue);
	}

	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException
	 */
	public static final Person buildPerson(JSONObject responseValue)
			throws DycapoException {
		Log.d(TAG, "buildPerson");
		return PersonFetcher.fetchPerson(responseValue);
	}

	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException
	 */
	public static final Mode buildMode(JSONObject responseValue)
			throws DycapoException {
		Log.d(TAG, "buildMode");
		return ModeFetcher.fetchMode(responseValue);
	}

	/**
	 * @param responseValue
	 * @return
	 * @throws DycapoException
	 */
	public static final Participation buildParticipation(
			JSONObject responseValue) throws DycapoException {
		Log.d(TAG, "buildParticipation");
		return ParticipationFetcher.fetchParticipation(responseValue);
	}

	public static final Search buildSearch(JSONObject responseValue)
			throws DycapoException {
		Log.d(TAG, "buildSearch");
		return SearchFetcher.fetchSearch(responseValue);
	}

	public static final List<Participation> extractTripParticipations(
			JSONArray json) throws DycapoException {
		Log.d(TAG, "extractTripParticipations");
		return ParticipationFetcher.fetchTripParticipations(json);
	}
}
