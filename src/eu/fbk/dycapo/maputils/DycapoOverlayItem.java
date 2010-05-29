/**
 * 
 */
package eu.fbk.dycapo.maputils;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 *
 */
public class DycapoOverlayItem extends OverlayItem {

	private Person person;
	/**
	 * @param point
	 * @param title
	 * @param snippet
	 */
	public DycapoOverlayItem(GeoPoint point, String title, String snippet, Person p) {
		super(point, p.getUsername(),snippet);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	
	

}
