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
		this.setActive(null);
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
		this.setActive(false);
		
	}
	
	public ActiveTrip (Trip trip){
		this.author = trip.getAuthor();
		this.content = trip.getContent();
		this.expires = trip.getExpires();
		this.published = trip.getPublished();
		this.updated =	trip.getUpdated();
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
	
	public void addParticipant(Participation participant){
		if (this.mActive instanceof Boolean)
			if (!this.mActive)this.mActive = true;
		if (this.mParticipants == null) this.mParticipants = new ArrayList<Participation>();
		this.mParticipants.add(participant);
	}
	
	public void addParticipant(String mRole, Person mParticipant, Date mTime){
		if (this.mActive instanceof Boolean)
			if (!this.mActive)this.mActive = true;
		if (this.mParticipants == null) this.mParticipants = new ArrayList<Participation>();
		this.mParticipants.add(new Participation(mRole,mParticipant,mTime));
	}
	
	public void removeParticipant(String username){
		Person fetch;
		int size = this.mParticipants.size();
			for (int i = 0 ; i<size ; i++){
				fetch = this.mParticipants.get(i).getmParticipant();
				if (fetch instanceof Person)
					if (username instanceof String)
						if(fetch.getUsername().equals(username))
							this.mParticipants.remove(i);	
			}
	
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
