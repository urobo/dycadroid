/**
 * 
 */
package eu.fbk.dycapo.factories.bundles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.models.Search;
import eu.fbk.dycapo.models.Trip;

/**
 * @author riccardo
 * 
 */
public abstract class SearchBundle {
	private static final String TAG = "SearchBundle";

	public static final Bundle toBundle(Search search) {
		Bundle data = new Bundle();
		Log.d(TAG, "Search toBundle");
		if (search.getAuthor() instanceof Person)
			data.putBundle(Search.AUTHOR,
					PersonBundle.toBundle(search.getAuthor()));

		if (search.getOrigin() instanceof Location)
			data.putBundle(Search.ORIGIN,
					LocationBundle.toBundle(search.getOrigin()));

		if (search.getDestination() instanceof Location)
			data.putBundle(Search.DESTINATION,
					LocationBundle.toBundle(search.getDestination()));

		if ((search.getTrips() != null) && !(search.getTrips().isEmpty())) {
			Bundle trips = new Bundle();
			int size = search.getTrips().size();
			for (int i = 0; i < size; i++) {
				Trip temp = search.getTrips().get(i);
				trips.putBundle(String.valueOf(i), TripBundle.toBundle(temp));
			}

			data.putBundle(Search.TRIPS, trips);
		}

		if (search.getHref() instanceof String)
			data.putString(Search.HREF, search.getHref());

		return data;
	}

	public static final Search fromBundle(Bundle data) {
		Search search = new Search();
		Log.d(TAG, "Search fromBundle");
		if (data.containsKey(Search.AUTHOR))
			search.setAuthor(PersonBundle.fromBundle(data
					.getBundle(Search.AUTHOR)));

		if (data.containsKey(Search.ORIGIN))
			search.setOrigin(LocationBundle.fromBundle(data
					.getBundle(Search.ORIGIN)));

		if (data.containsKey(Search.DESTINATION))
			search.setDestination(LocationBundle.fromBundle(data
					.getBundle(Search.DESTINATION)));

		if (data.containsKey(Search.TRIPS)) {
			Bundle temp = data.getBundle(Search.TRIPS);
			Iterator<String> i = temp.keySet().iterator();
			List<Trip> trips = new ArrayList<Trip>();
			while (i.hasNext())
				trips.add(TripBundle.fromBundle(data.getBundle(i.next())));
			search.setTrips(trips);
		}

		if (data.containsKey(Search.HREF))
			search.setHref(data.getString(Search.HREF));

		return search;
	}
}
