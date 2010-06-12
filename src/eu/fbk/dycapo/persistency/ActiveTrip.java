/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		this.content = trip.getContent();
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
	
}
