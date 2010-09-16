/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Preferences;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 *
 */
public class ActiveTrip extends Trip {
	
	public static final String ACTIVE = "active";
	
	private List<Participation> mParticipants;
	private Boolean mActive;
	private Route mRoute;

	/**
	 * 
	 */
	public ActiveTrip() {
		
		super();
		this.mParticipants = new ArrayList<Participation>();
		this.setActive(true);
	}

	/**
	 * @param expires
	 * @param author
	 * @param mode
	 * @param preferences
	 * @param origin
	 * @param destination
	 */
	public ActiveTrip(Date expires, Person author, Mode mode,
			Preferences preferences, Location origin, Location destination) {
		
		super(expires, author, mode, preferences, origin, destination);
		this.mParticipants = new ArrayList<Participation>();
		this.setActive(true);
		
	}
	
	public ActiveTrip (Trip trip){
		if (trip.getId() instanceof Integer)
			this.id = trip.getId();
		
		this.author = trip.getAuthor();
		
		this.destination = trip.getDestination();
		this.origin = trip.getOrigin();
		this.preferences = trip.getPreferences();
		this.mode = trip.getMode();
		this.waypoints = trip.getWaypoints();
		
		this.expires = trip.getExpires();
		this.published = trip.getPublished();
		this.updated =	trip.getUpdated();
		this.mParticipants = new ArrayList<Participation>();
		this.setActive(true);
		this.mRoute = null;
	}

	/**
	 * @return the mParticipants
	 */
	public List<Participation> getmParticipants() {
		return mParticipants;
	}

	/**
	 * @param mParticipants the mParticipants to set
	 */
	public void setmParticipants(ArrayList<Participation> mParticipants) {
		this.mParticipants = mParticipants;
	}
	


	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.mActive = active;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return mActive;
	}


	/**
	 * @return the route
	 */
	public Route getRoute() {
		return mRoute;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(Route route) {
		this.mRoute = route;
	}

	/* (non-Javadoc)
	 * @see eu.fbk.dycapo.models.Trip#toJSONObject()
	 */
	@Override
	public JSONObject toJSONObject() {
		JSONObject result = super.toJSONObject();
		if (this.mActive instanceof Boolean)
			try {
				result.put(ActiveTrip.ACTIVE, this.mActive.booleanValue());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	
	
}
