/**
 * 
 */
package eu.fbk.dycapo.models;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		public static final String LOCATIONS="locations";
		
		private Mode mode;					//must
		private Preferences preferences;	//must
		private Location origin;			//must
		private Location destination;		//must
		private ArrayList<Location> waypoints;  //may

		
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


		@Override
		public HashMap<String, Object> toHashMap() {
			HashMap<String,Object> result = new HashMap<String,Object>();
			ArrayList<HashMap<String,Object>> locations = new ArrayList<HashMap<String,Object>>();
			if (this.origin instanceof eu.fbk.dycapo.models.Location)locations.add(this.origin.toHashMap());
			if (this.destination instanceof eu.fbk.dycapo.models.Location)locations.add(this.destination.toHashMap());
			if (this.mode instanceof eu.fbk.dycapo.models.Mode)result.put(Trip.Content.MODE, this.mode.toHashMap());
			if (this.preferences instanceof eu.fbk.dycapo.models.Preferences)result.put(Trip.Content.PREFERENCES,this.preferences.toHashMap());
		    if (this.waypoints instanceof ArrayList<?>){
		    	int size= this.waypoints.size();
		    	
		    	for (int i = 0 ; i< size ; i++)
		    		locations.add(this.waypoints.get(i).toHashMap());
		    	
		    }
		    result.put(Trip.Content.LOCATIONS, locations.toArray());
			return result;
		}
	}
	
	/**
	 * @return the content
	 */
	public Content getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Content content) {
		this.content = content;
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
	 * @return the origin
	 */
	public Location getOrigin() {
		return content.origin;
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
	 * @return the mode
	 */
	public Mode getMode() {
		return this.content.mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(Mode mode) {
		this.content.mode = mode;
	}

	/**
	 * @return the preferences
	 */
	public Preferences getPreferences() {
		return this.content.preferences;
	}

	/**
	 * @param preferences the preferences to set
	 */
	public void setPreferences(Preferences preferences) {
		this.content.preferences = preferences;
	}
	
	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (this.author instanceof eu.fbk.dycapo.models.Person)result.put(Trip.AUTHOR,this.author.toHashMap());
		if (this.content instanceof eu.fbk.dycapo.models.Trip.Content)result.put(Trip.CONTENT,this.content.toHashMap());
		
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		
		
		if (this.expires instanceof java.util.Date)result.put(Trip.EXPIRES,formatter.format(this.expires));
		if (this.published instanceof java.util.Date)result.put(Trip.PUBLISHED,formatter.format(this.published));
		if (this.updated instanceof java.util.Date)result.put(Trip.UPDATED, formatter.format(this.updated));
		return result;
	}
}
