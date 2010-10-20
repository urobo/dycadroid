/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import eu.fbk.dycapo.activities.Navigation;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.json.DycapoObjectsFetcher;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;

/**
 * @author riccardo
 *
 */
public class LocationService implements LocationListener {
	private static final String TAG = "LocationService";
	public LocationManager lmr = null;
	private Navigation SystemService = null;
	
	public LocationService(Navigation sservice){
		this.SystemService = sservice;
	}
	
	public void startLocationService(){
		Log.d(TAG, "starting LocationService");
		this.lmr = (LocationManager) this.SystemService.getSystemService(Context.LOCATION_SERVICE);
		this.lmr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 5, this);
	}
	
	public void stopLocationService(){
		this.lmr.removeUpdates(this);
	}
	
	/* (non-Javadoc)
	 * @see android.location.LocationListener#onLocationChanged(android.location.Location)
	 */
	@Override
	public void onLocationChanged(Location location) {
        location = this.lmr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        try {
            eu.fbk.dycapo.models.Location loc = new eu.fbk.dycapo.models.Location();
            Log.d(TAG, "longitude : " + location.getLongitude());
            Log.d(TAG, "latitude : " + location.getLatitude());
            loc.setGeorss_point(String.valueOf((double)location.getLongitude())+","+String.valueOf((double)location.getLatitude()));
            loc.setLeaves(Calendar.getInstance().getTime());
            loc.setPoint(eu.fbk.dycapo.models.Location.POSI);
            LocationService.updatePosition(loc);
            
           } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
	 */
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
	 */
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onStatusChanged(java.lang.String, int, android.os.Bundle)
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
	
	
	public static final void updatePosition(eu.fbk.dycapo.models.Location loc){
		
		User usr = DBPerson.getUser();
		try {
			DycapoServiceClient.callDycapo(
					DycapoServiceClient.PUT, 
					DycapoServiceClient.uriBuilder("persons/"+usr.getUsername()+"/location"), 
					loc.toJSONObject(), 
					usr.getUsername(), 
					usr.getPassword());
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static final eu.fbk.dycapo.models.Location getPosition (Person p){
		User usr = DBPerson.getUser();
		try {
			JSONObject json = DycapoServiceClient.callDycapo(DycapoServiceClient.GET, 
					DycapoServiceClient.uriBuilder("persons/" + p.getUsername() + "location"),
					null,
					usr.getUsername(), 
					usr.getPassword());
			eu.fbk.dycapo.models.Location ploc= DycapoObjectsFetcher.buildLocation(json);
			return ploc;
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.e(TAG,e.getMessage());
			e.printStackTrace();
		}
		return null;
		
	}

}
