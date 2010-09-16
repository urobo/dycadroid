/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.persistency.ActiveTrip;

/**
 * @author riccardo
 *
 */
public abstract class ActiveTripFetcher {
	private static final String TAG = "ActiveTripFetcher";
	public static final ActiveTrip fetchActiveTrip(JSONObject jsonObj){
		try {
			ActiveTrip aTrip =(ActiveTrip) TripFetcher.fetchTrip(jsonObj);
			if(jsonObj.has(ActiveTrip.ACTIVE))
				aTrip.setActive(jsonObj.getBoolean(ActiveTrip.ACTIVE));
			return aTrip;
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
