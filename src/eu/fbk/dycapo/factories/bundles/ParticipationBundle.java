/**
 * 
 */
package eu.fbk.dycapo.factories.bundles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import eu.fbk.dycapo.models.Participation;
import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 * 
 */
public abstract class ParticipationBundle {
	private static final String TAG = "ParticipationBundle";

	public static final Bundle toBundle(Participation p) {
		Bundle data = new Bundle();
		Log.d(TAG, "toBundle");
		if (p.getHref() instanceof String)
			data.putString(Participation.HREF, p.getHref());

		if (p.getAuthor() instanceof Person)
			data.putBundle(Participation.AUTHOR,
					PersonBundle.toBundle(p.getAuthor()));

		if (p.getStatus() instanceof String)
			data.putString(Participation.STATUS, p.getStatus());

		if (p.getRole() instanceof String)
			data.putString(Participation.ROLE, p.getRole());
		return data;
	}

	public static final Participation fromBundle(Bundle data) {
		Participation p = new Participation();
		Log.d(TAG, "fromBundle");
		if (data.containsKey(Participation.HREF))
			p.setHref(data.getString(Participation.HREF));

		if (data.containsKey(Participation.AUTHOR))
			p.setAuthor(PersonBundle.fromBundle(data
					.getBundle(Participation.AUTHOR)));

		if (data.containsKey(Participation.STATUS))
			p.setStatus(data.getString(Participation.STATUS));

		if (data.containsKey(Participation.ROLE))
			p.setRole(data.getString(Participation.ROLE));

		return p;
	}

	public static final Bundle encapsulateParticipations(
			List<Participation> list) {
		Bundle bundle = new Bundle();
		int size = list.size();
		for (int i = 0; i < size; i++)
			if (list.get(i) instanceof Participation) {
				bundle.putBundle(list.get(i).getHref(),
						ParticipationBundle.toBundle(list.get(i)));
			}
		return bundle;
	}

	public static final List<Participation> unboxParticipations(Bundle bundle) {
		List<Participation> list = new ArrayList<Participation>();
		Iterator<String> keys = bundle.keySet().iterator();
		while (keys.hasNext())
			list.add(ParticipationBundle.fromBundle(bundle.getBundle(keys
					.next())));

		return list;
	}
}
