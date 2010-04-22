/**
 * 
 */
package eu.fbk.dycapo.models;


import java.util.Date;
import java.util.HashMap;

import eu.fbk.dycapo.xmlrpc.XMLRPCModel;


/**
 * @author riccardo
 *
 */


public class Trip implements XMLRPCModel {
	public static final String PUBLISHED="published";
	public static final String UPDATED="updated";
	public static final String EXPIRES="expires";
	public static final String CONTENT="content";
	public static final String AUTHOR="author";

	
	private Date published;			//may
	private Date updated;			//should
	private Date expires;			//must
	private Person author;			//must
	private Content content;		//must
	
	
	public class Content implements XMLRPCModel{
		public static final String MODE="mode";
		public static final String PREFERENCES="preferences";
		public static final String ORIGIN="origin";
		public static final String DESTINATION="destination";
		public static final String WAYPOINTS="waypoints";
		
		private Mode mode;					//must
		private Preferences preferences;	//must
		private Location origin;			//must
		private Location destination;		//must
		private Location[] waypoints;		//may
		
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

		@Override
		public HashMap<String, Object> toHashMap() {
			HashMap<String,Object> result = new HashMap<String,Object>();
			if (this.destination instanceof eu.fbk.dycapo.models.Location)result.putAll(this.destination.toHashMap());
			if (this.mode instanceof eu.fbk.dycapo.models.Mode)result.putAll(this.mode.toHashMap());
			if (this.origin instanceof eu.fbk.dycapo.models.Location)result.putAll(this.origin.toHashMap());
			if (this.preferences instanceof eu.fbk.dycapo.models.Preferences)result.putAll(this.preferences.toHashMap());
			int length = this.waypoints.length;
			for(int i = 0 ; i < length ; i++)
				if(this.waypoints[i] instanceof eu.fbk.dycapo.models.Location)result.putAll(this.waypoints[i].toHashMap());			
			return result;
		}

	}

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
		return this.content.getMode();
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(Mode mode) {
		this.content.setMode(mode);
	}

	/**
	 * @return the preferences
	 */
	public Preferences getPreferences() {
		return this.content.getPreferences();
	}

	/**
	 * @param preferences the preferences to set
	 */
	public void setPreferences(Preferences preferences) {
		this.content.setPreferences(preferences);
	}

	/**
	 * @return the origin
	 */
	public Location getOrigin() {
		return this.content.getOrigin();
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(Location origin) {
		this.content.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public Location getDestination() {
		return this.content.destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Location destination) {
		this.content.destination = destination;
	}

	/**
	 * @return the waypoints
	 */
	public Location[] getWaypoints() {
		return this.content.waypoints;
	}

	/**
	 * @param waypoints the waypoints to set
	 */
	public void setWaypoints(Location[] waypoints) {
		this.content.waypoints = waypoints;
	}
	
	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (this.author instanceof eu.fbk.dycapo.models.Person)result.put(Trip.AUTHOR,this.author);
		if (this.content instanceof eu.fbk.dycapo.models.Trip.Content)result.putAll(this.content.toHashMap());
		if (this.expires instanceof java.util.Date)result.put(Trip.EXPIRES,this.expires.toString());
		if (this.published instanceof java.util.Date)result.put(Trip.PUBLISHED,this.published.toString());
		if (this.updated instanceof java.util.Date)result.put(Trip.UPDATED, this.updated.toString());

		return result;
	}
}
