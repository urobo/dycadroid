/**
 * 
 */
package eu.fbk.dycapo.models;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import eu.fbk.dycapo.factories.json.DycapoJSONObjects;

/**
 * @author riccardo
 *
 */
public class Search implements DycapoJSONObjects{
	
	private static final String TAG = "Search";
	
	public static final String ORIGIN = "origin";
	public static final String DESTINATION = "destination";
	public static final String AUTHOR = "author";
	public static final String TRIPS = "trips";
	
	private Location origin;
	private Location destination;
	private Person author;
	private List<Trip> trips;
	private String href;
	/**
	 * @return the origin
	 */
	public Location getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(Location origin) {
		this.origin = origin;
	}
	/**
	 * @return the destination
	 */
	public Location getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Location destination) {
		this.destination = destination;
	}
	/**
	 * @return the author
	 */
	public Person getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(Person author) {
		this.author = author;
	}
	/**
	 * @return the trips
	 */
	public List<Trip> getTrips() {
		return trips;
	}
	/**
	 * @param trips the trips to set
	 */
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}
	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}
	
	@Override
	public JSONObject toJSONObject() {
		JSONObject jsonObj = new JSONObject();
		try {
			if (this.author instanceof Person)
				jsonObj.put(AUTHOR, this.author.toJSONObject());
			
			if (this.destination instanceof Location)
				jsonObj.put(DESTINATION, this.destination.toJSONObject());
				
			if (this.origin instanceof Location)
				jsonObj.put(ORIGIN, this.origin.toJSONObject());
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	
}
