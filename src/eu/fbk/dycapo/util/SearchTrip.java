/**
 * 
 */
package eu.fbk.dycapo.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.json.DycapoObjectsFetcher;
import eu.fbk.dycapo.models.Search;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;

/**
 * @author riccardo
 * 
 */
public abstract class SearchTrip {
	private static final String TAG = "SearchTrip";

	public static final Search searchTrips(Search st) {

		User usr = DBPerson.getUser();
		try {
			JSONObject createSearch = DycapoServiceClient.callDycapo(
					DycapoServiceClient.POST,
					DycapoServiceClient.uriBuilder("searches"),
					st.toJSONObject(), usr.getUsername(), usr.getPassword());
			st.setHref(DycapoObjectsFetcher.buildSearch(createSearch).getHref());

			JSONObject retrieveSearch = DycapoServiceClient.callDycapo(
					DycapoServiceClient.GET, st.getHref(), null,
					usr.getUsername(), usr.getPassword());
			st.setTrips(DycapoObjectsFetcher.buildSearch(retrieveSearch)
					.getTrips());

			return st;
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
