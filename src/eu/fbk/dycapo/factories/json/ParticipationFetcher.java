/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 *
 */
public abstract class ParticipationFetcher {
	private static final String TAG = "ParticipationFetcher";
	
	public static final Participation fetchParticipation(JSONObject jsonObject) throws DycapoException{
		Participation participation = new Participation();
		if (jsonObject instanceof JSONObject)Log.d(TAG, jsonObject.toString());
		try {
			Log.d(TAG, "participation : " + participation.toString());
			if (jsonObject.has(DycapoObjectsFetcher.HREF)){
				participation.setHref(jsonObject.getString(DycapoObjectsFetcher.HREF));
				Log.d(TAG, participation.getHref());
			}
			
			if (jsonObject.has(Participation.STATUS)){
				participation.setStatus(jsonObject.getString(Participation.STATUS));
				Log.d(TAG, participation.getStatus());
			}
			
			if (jsonObject.has(Participation.AUTHOR)){
				participation.setAuthor(PersonFetcher.fetchPerson(jsonObject.getJSONObject(Participation.AUTHOR)));
				if (participation.getAuthor() instanceof Person)
					Log.d(TAG, "Author fetched");
				
				//Log.d(TAG, participation.getAuthor().toString());
			}
			
			//Log.d(TAG, "participation : " + participation.toString());
			return participation;
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		

		return participation;
	}
	
	public static final List<Participation> fetchTripParticipations(JSONArray json) throws DycapoException{
		List<Participation> tparticipations = new ArrayList<Participation>();
		try{
			int size = json.length();
			Log.d(TAG + ".fetchTripParticipations", "participations size : " + String.valueOf(size));
			for (int i = 0 ; i< size;i++){
				Participation p = fetchParticipation(json.getJSONObject(i));
				
				Log.d(TAG,"fetching : " + i + "-th participation");
				tparticipations.add(p);
				Log.d(TAG + ".fetchTripParticipations", "done!");
			}
			if (tparticipations.isEmpty())
				Log.d(TAG + ".fetchTripParticipations","empty list" );
			else Log.d(TAG + ".fetchTripParticipations", String.valueOf(tparticipations.size()));
			return tparticipations;
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return tparticipations;
		
	}
}
