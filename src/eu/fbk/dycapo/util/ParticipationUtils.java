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
import eu.fbk.dycapo.persistency.DBParticipation;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;

/**
 * @author riccardo
 * 
 */
public abstract class ParticipationUtils {
	private static final String TAG = "ParticipationUtils";

	public static final Participation checkParticipationStatus(Participation p) {
		User usr = DBPerson.getUser();
		try {
			JSONObject json = DycapoServiceClient.callDycapo(
					DycapoServiceClient.GET, p.getHref(), null,
					usr.getUsername(), usr.getPassword());
			p = DycapoObjectsFetcher.buildParticipation(json);
			return p;
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return p;
	}

	public static final List<Participation> getListOfParticipations(Trip p) {
		User usr = DBPerson.getUser();
		Log.d(TAG, p.getHref());
		try {
			JSONArray tps = DycapoServiceClient.callDycapoForDataCollections(
					DycapoServiceClient.GET, p.getHref() + "participations/",
					null, usr.getUsername(), usr.getPassword());
			List<Participation> result = DycapoObjectsFetcher
					.extractTripParticipations(tps);
			if (result.isEmpty())
				Log.d(TAG, "participation list is empty");
			else
				Log.d(TAG, String.valueOf(result.size()));
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

	public static final void updateDycapoParticipation(Participation p) {
		User usr = DBPerson.getUser();
		try {
			DycapoServiceClient.callDycapo(DycapoServiceClient.PUT,
					p.getHref(), p.toJSONObject(), usr.getUsername(),
					usr.getPassword());
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
	}

	public static final void postParticipation(Trip trip) {
		User usr = DBPerson.getUser();
		Participation p = new Participation();
		p.setAuthor(usr);
		p.setStatus(Participation.REQUESTED);
		try {
			JSONObject json = DycapoServiceClient.callDycapo(
					DycapoServiceClient.POST, trip.getHref()
							+ "participations/", p.toJSONObject(),
					usr.getUsername(), usr.getPassword());
			p = DycapoObjectsFetcher.buildParticipation(json);
			DBParticipation.addParticipation(p);
			p = null;
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}

	}

	public static final void acceptRideRequest(Participation p) {
		User usr = DBPerson.getUser();
		try {
			DycapoServiceClient.callDycapo(DycapoServiceClient.PUT,
					p.getHref(), p.toJSONObject(), usr.getUsername(),
					usr.getPassword());
			DBParticipation.updateParticipation(p);
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}

	}

	public static final void refuseRideRequest(Participation p) {
		User usr = DBPerson.getUser();
		try {
			DycapoServiceClient.callDycapo(DycapoServiceClient.DELETE,
					p.getHref(), null, usr.getUsername(), usr.getPassword());
			DBParticipation.removeParticipation(p);
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}

	}
}
