/**
 * 
 */
package eu.fbk.dycapo.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.json.DycapoObjectsFetcher;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.models.Trip;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;

/**
 * @author riccardo
 *
 */
public abstract class ParticipationUtils {
	private static final String TAG= "ParticipationUtils"; 
	public static final Participation checkParticipationStatus(Participation p){
		User usr = DBPerson.getUser();
		try {
			JSONObject json = DycapoServiceClient.callDycapo(
					DycapoServiceClient.GET,
					p.getHref(), 
					null, 
					usr.getUsername(), 
					usr.getPassword());
			p = DycapoObjectsFetcher.buildParticipation(json);
			return p;
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG,e.getMessage());
			e.printStackTrace();
		}
		return p;
	}
	
	public static final List<Participation> getListOfParticipations(Trip p){
		User usr = DBPerson.getUser();
		try {
			JSONArray tps =new JSONArray(DycapoServiceClient.callDycapo(
					DycapoServiceClient.GET, 
					p.getHref()+ "participations/",
					null, 
					usr.getUsername(), 
					usr.getPassword()).toString());
			List<Participation> result = DycapoObjectsFetcher.extractTripParticipations(tps);
			tps = null;
			return result;
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
