/**
 * 
 */
package eu.fbk.dycapo.bundles;

import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.models.Person;
import android.os.Bundle;

/**
 * @author riccardo
 *
 */
public abstract class ParticipationBundle {
	private static final String TAG = "ParticipationBundle";
	
	public static final Bundle toBundle(Participation p){
		Bundle data = new Bundle();
		
		if (p.getHref() instanceof String)
			data.putString(Participation.HREF, p.getHref());
		
		if (p.getPerson() instanceof Person)
			data.putBundle(Participation.PERSON, PersonBundle.toBundle(p.getPerson()));
		
		if (p.getStatus() instanceof String)
			data.putString(Participation.STATUS,p.getStatus());
		
		return data;
	}
	
	public static final Participation fromBundle(Bundle data){
		Participation p = new Participation();
		
		if (data.containsKey(Participation.HREF))
			p.setHref(data.getString(Participation.HREF));
		
		if (data.containsKey(Participation.PERSON))
			p.setPerson(PersonBundle.fromBundle(data.getBundle(Participation.PERSON)));
		
		if (data.containsKey(Participation.STATUS))
			p.setStatus(data.getString(Participation.STATUS));
		
		return p;
	}
}
