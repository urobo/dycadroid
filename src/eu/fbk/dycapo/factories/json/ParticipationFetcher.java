/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Participation;

/**
 * @author riccardo
 *
 */
public abstract class ParticipationFetcher {
	private static final String TAG = "ParticipationFetcher";
	
	public static final Participation fetchParticipation(JSONObject jsonObject) throws DycapoException{
		Participation participation = new Participation();
		try {
		if (jsonObject.has(Participation.PERSON))
			participation.setPerson(PersonFetcher.fetchPerson(jsonObject.getJSONObject(Participation.PERSON)));
		if (jsonObject.has(Participation.STATUS))
			participation.setStatus(jsonObject.getString(Participation.STATUS));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}