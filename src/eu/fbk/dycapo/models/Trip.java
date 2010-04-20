/**
 * 
 */
package eu.fbk.dycapo.models;


import java.util.Date;
import java.util.HashMap;


/**
 * @author riccardo
 *
 */
public class Trip implements XMLRPCModel {
	private static final String PUBLISHED="published";
	private static final String UPDATED="updated";
	private static final String EXPIRES="expires";
	private static final String CONTENT="content";
	private static final String ACTIVE="active";
	private static final String AUTHOR="author";
	private static final String MODE="mode";
	private static final String PREFERENCES="preferences";
	private static final String ORIGIN="origin";
	private static final String DESTINATION="destination";
	private static final String WAYPOINTS="waypoints";
	
	private Date published;
	private Date updated;
	private Date expires;
	private String content;
	private boolean active;
	private Person author;
	private Mode mode;
	private Preferences preferences;
	private Location origin;
	private Location destination;
	private Location[] waypoints;
	
	


	/**
	 * 
	 */
	public Trip(){
		
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	public Location[] getWaypoints() {
		return waypoints;
	}

	/**
	 * @param waypoints the waypoints to set
	 */
	public void setWaypoints(Location[] waypoints) {
		this.waypoints = waypoints;
	}
	
	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put(Trip.ACTIVE,this.active);
		result.put(Trip.AUTHOR,this.author);
		result.put(Trip.CONTENT,this.content);
		result.putAll(this.destination.toHashMap());
		result.put(Trip.EXPIRES,this.expires);
		result.putAll(this.mode.toHashMap());
		result.putAll(this.origin.toHashMap());
		result.putAll(this.preferences.toHashMap());
		result.put(Trip.PUBLISHED,this.published);
		result.put(Trip.UPDATED, this.updated);
		int length= this.waypoints.length;
		for (int i = 0 ; i< length ; i++)
			result.putAll(this.waypoints[i].toHashMap());
		return result;
	}
}
