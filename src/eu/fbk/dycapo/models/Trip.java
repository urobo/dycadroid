/**
 * 
 */
package eu.fbk.dycapo.models;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import eu.fbk.dycapo.factories.json.DycapoJSONObjects;


/**
 * @author riccardo
 *
 */


public class Trip implements DycapoJSONObjects {
	public static final String TAG = "Trip";
	
	public static final String ID="id";
	public static final String PUBLISHED="published";
	public static final String UPDATED="updated";
	public static final String EXPIRES="expires";
	public static final String CONTENT="content";
	public static final String AUTHOR="author";
	public static final String MODE="modality";
	public static final String PREFERENCES="preferences";
	public static final String LOCATIONS="locations";
	
	protected Integer id;
	protected Date published;			//may
	protected Date updated;			//should
	protected Date expires;			//must
	protected Person author;			//must
	protected Mode mode;					//must
	protected Preferences preferences;	//must
	protected Location origin;			//must
	protected Location destination;		//must
	protected ArrayList<Location> waypoints;		//must
	protected String href;
	/**
	 * @param expires
	 * @param author
	 * @param content
	 */
	public Trip(Date expires, Person author,Mode mode, Preferences preferences, Location origin,Location destination) {
		this.expires = expires;
		this.author = author;
		this.destination = destination;
		this.origin = origin;
		this.mode = mode;
		this.preferences = preferences;
	}
	
	
	public Trip() {
		
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the published
	 */
	public Date getPublished() {
		return published;
	}


	/**
	 * @param published the published to set
	 */
	public void setPublished(Date published) {
		this.published = published;
	}


	/**
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}


	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}


	/**
	 * @return the expires
	 */
	public Date getExpires() {
		return expires;
	}


	/**
	 * @param expires the expires to set
	 */
	public void setExpires(Date expires) {
		this.expires = expires;
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
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}


	/**
	 * @param mode the mode to set
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}


	/**
	 * @return the preferences
	 */
	public Preferences getPreferences() {
		return preferences;
	}


	/**
	 * @param preferences the preferences to set
	 */
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}


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
	 * @return the waypoints
	 */
	public ArrayList<Location> getWaypoints() {
		return waypoints;
	}


	/**
	 * @param waypoints the waypoints to set
	 */
	public void setWaypoints(ArrayList<Location> waypoints) {
		this.waypoints = waypoints;
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
		JSONObject result = new JSONObject();
		try{
			if (this.id instanceof Integer) 
				result.put(Trip.ID, this.id.intValue());
			
			if (this.author instanceof eu.fbk.dycapo.models.Person)
				result.put(Trip.AUTHOR,this.author.toJSONObject());
			
			JSONArray locations = new JSONArray();
			
			if (this.origin instanceof eu.fbk.dycapo.models.Location)
				locations.put(this.origin.toJSONObject());
			
			if (this.destination instanceof eu.fbk.dycapo.models.Location)
				locations.put(this.destination.toJSONObject());
			
			if (this.mode instanceof eu.fbk.dycapo.models.Mode)
				result.put(Trip.MODE, this.mode.toJSONObject());
			
			if (this.preferences instanceof eu.fbk.dycapo.models.Preferences)
				result.put(Trip.PREFERENCES,this.preferences.toJSONObject());
			
		    if (this.waypoints instanceof ArrayList<?>){
		    	int size= this.waypoints.size();
		    	
		    	for (int i = 0 ; i< size ; i++)
		    		locations.put(this.waypoints.get(i).toJSONObject());
		    	
		    }
		    result.put(Trip.LOCATIONS, locations);
			
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			
			
			if (this.expires instanceof java.util.Date){
				result.put(Trip.EXPIRES,formatter.format(this.expires));
				Log.i(Trip.EXPIRES, formatter.format(this.expires));
			}
				
			if (this.published instanceof java.util.Date)
				result.put(Trip.PUBLISHED,formatter.format(this.published));
			
			if (this.updated instanceof java.util.Date)
				result.put(Trip.UPDATED, formatter.format(this.updated));
			
			return result;
	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
