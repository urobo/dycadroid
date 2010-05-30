/**
 * 
 */
package eu.fbk.dycapo.models;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.os.Bundle;
import android.util.Log;

import eu.fbk.dycapo.services.BundleModel;
import eu.fbk.dycapo.xmlrpc.XMLRPCModel;


/**
 * @author riccardo
 *
 */


public class Trip implements XMLRPCModel,BundleModel {
	public static final String TAG = "Trip";
	
	public static final String ID="id";
	public static final String PUBLISHED="published";
	public static final String UPDATED="updated";
	public static final String EXPIRES="expires";
	public static final String CONTENT="content";
	public static final String AUTHOR="author";

	private Integer id;
	private Date published;			//may
	private Date updated;			//should
	private Date expires;			//must
	private Person author;			//must
	private Content content;		//must

	/**
	 * @param expires
	 * @param author
	 * @param content
	 */
	public Trip(Date expires, Person author,Mode mode, Preferences preferences, Location origin,Location destination) {
		this.expires = expires;
		this.author = author;
		this.content= new Content();
		this.content.setDestination(destination);
		this.content.setOrigin(origin);
		this.content.setMode(mode);
		this.content.setPreferences(preferences);
	}
	public void createContent(){
		this.content= new Content();
	}
	
	public class Content implements XMLRPCModel,BundleModel{
		public static final String MODE="mode";
		public static final String PREFERENCES="prefs";
		public static final String LOCATIONS="locations";
		
		private Mode mode;					//must
		private Preferences preferences;	//must
		private Location origin;			//must
		private Location destination;		//must
		private ArrayList<Location> waypoints;  //may

		public Content(){
			
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

		@Override
		public void fromBundle(Bundle data) {
			
			if (data.containsKey(Trip.Content.LOCATIONS)){
				Bundle locations = data.getBundle(Trip.Content.LOCATIONS);
				
				if (locations.containsKey(Location.POINT_TYPE[Location.ORIG]))
					this.origin.fromBundle((locations.getBundle(Location.POINT_TYPE[Location.ORIG])));
			
				if (locations.containsKey(Location.POINT_TYPE[Location.DEST]))
					this.destination.fromBundle((locations.getBundle(Location.POINT_TYPE[Location.DEST])));
				
				int i = 0;
				while (locations.containsKey(Location.POINT_TYPE[Location.WAYP] + String.valueOf(i))){
					Location wayp  = new Location();
					wayp.fromBundle(locations.getBundle(Location.POINT_TYPE[Location.WAYP] + String.valueOf(i)));
					this.waypoints.add(wayp);
				}
								
				if (data.containsKey(Trip.Content.PREFERENCES))
					this.preferences.fromBundle(data.getBundle(Trip.Content.PREFERENCES));
				
				if (data.containsKey(Trip.Content.MODE))
					this.mode.fromBundle(data.getBundle(Trip.Content.MODE));
				
			}
			
		}

		@Override
		public Bundle toBundle() {
			Bundle result = new Bundle();
			Bundle locations = new Bundle();
			
			if (this.origin instanceof Location)
				locations.putBundle(Location.POINT_TYPE[Location.ORIG], this.origin.toBundle());
			
			if (this.destination instanceof Location)
				locations.putBundle(Location.POINT_TYPE[Location.DEST], this.destination.toBundle());
			
			int size = this.waypoints.size();
			for (int i = 0 ; i< size; i++)
				locations.putBundle(Location.POINT_TYPE[Location.WAYP]+ String.valueOf(i), this.waypoints.get(i).toBundle());
			
			if(!locations.isEmpty())
				result.putBundle(Trip.Content.LOCATIONS, locations);
			
			if (result.containsKey(Trip.Content.MODE))
				result.putBundle(Trip.Content.MODE, this.mode.toBundle());
			
			if (result.containsKey(Trip.Content.PREFERENCES))
					result.putBundle(Trip.Content.PREFERENCES, this.preferences.toBundle());
						
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
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (this.id instanceof Integer) result.put(Trip.ID, this.id);
		if (this.author instanceof eu.fbk.dycapo.models.Person)result.put(Trip.AUTHOR,this.author.toHashMap());
		if (this.content instanceof eu.fbk.dycapo.models.Trip.Content)result.put(Trip.CONTENT,this.content.toHashMap());
		
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		
		
		if (this.expires instanceof java.util.Date){
			result.put(Trip.EXPIRES,formatter.format(this.expires));
			Log.i(Trip.EXPIRES, formatter.format(this.expires));
		}
			
		if (this.published instanceof java.util.Date)result.put(Trip.PUBLISHED,formatter.format(this.published));
		if (this.updated instanceof java.util.Date)result.put(Trip.UPDATED, formatter.format(this.updated));
		return result;
	}
	@Override
	public void fromBundle(Bundle data) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		if (data.containsKey(Trip.AUTHOR))
			this.author.fromBundle(data.getBundle(Trip.AUTHOR));
		
		if (data.containsKey(Trip.CONTENT))
			this.content.fromBundle(data.getBundle(Trip.CONTENT));
		
		if (data.containsKey(Trip.EXPIRES)){	
			try {
				this.expires = sdf.parse(data.getString(Trip.EXPIRES));
			} catch (ParseException e) {
				Log.e(TAG, e.getMessage());
			}
		}
		
		if (data.containsKey(Trip.ID))
			this.id = data.getInt(Trip.ID);
		
		if (data.containsKey(Trip.PUBLISHED)){
			try {
				this.published = sdf.parse(data.getString(Trip.PUBLISHED));
			} catch (ParseException e) {
				Log.e(TAG, e.getMessage());
			}
		}
		
		if (data.containsKey(Trip.UPDATED)){
			try {
				this.updated = sdf.parse(data.getString(Trip.UPDATED));
			} catch (ParseException e) {
				Log.e(TAG, e.getMessage());
			}
		}
			
	}
	@Override
	public Bundle toBundle() {
		Bundle result = new Bundle();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		if (this.author instanceof Person)
			result.putBundle(Trip.AUTHOR, this.author.toBundle());
		
		if (this.content instanceof Trip.Content)
			result.putBundle(Trip.CONTENT, this.content.toBundle());
		
		if (this.expires instanceof Date)
			result.putString(Trip.EXPIRES, sdf.format(this.expires));
		
		if (this.id instanceof Integer)
			result.putInt(Trip.ID, this.id);
		
		if (this.published instanceof Date)
			result.putString(Trip.PUBLISHED, sdf.format(this.published));
		
		if (this.updated instanceof Date)
			result.putString(Trip.UPDATED, sdf.format(this.updated));
			
		return result;
	}
}
