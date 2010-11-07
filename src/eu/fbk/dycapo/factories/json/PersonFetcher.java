/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 * 
 */
public abstract class PersonFetcher {
	private static final String TAG = "PersonFetcher";

	public static final Person fetchPerson(JSONObject responseValue)
			throws DycapoException {
		Log.d(TAG, "Fetching person");
		Person result = new Person();
		try {

			if (responseValue.has(DycapoObjectsFetcher.HREF)) {
				result.setHref(responseValue
						.getString(DycapoObjectsFetcher.HREF));
				Log.d(TAG, result.getHref());
			}
			// must
			// username
			if (responseValue.has(Person.USERNAME)) {
				result.setUsername(responseValue.getString(Person.USERNAME));
				Log.d(TAG, result.getUsername());
			}

			// may / should
			if (responseValue.has(Person.EMAIL))
				result.setEmail(responseValue.getString(Person.EMAIL));
			if (responseValue.has(Person.FIRST_NAME))
				result.setFirst_name(responseValue.getString(Person.FIRST_NAME));
			if (responseValue.has(Person.LAST_NAME))
				result.setLast_name(responseValue.getString(Person.LAST_NAME));
			if (responseValue.has(Person.URL))
				result.setUrl(responseValue.getString(Person.URL));
			if (responseValue.has(Person.PHONE))
				result.setPhone(responseValue.getString(Person.PHONE));
			if (responseValue.has(Person.POSITION)) {
				Log.d(TAG, "fetching position");
				result.setPosition(LocationFetcher.fetchLocation(responseValue
						.getJSONObject(Person.POSITION)));
				if (result.getPosition() instanceof Location)
					Log.d(TAG, "position fetched");
			}

			if (responseValue.has(Person.AGE)) {
				result.setAge(responseValue.getInt(Person.AGE));
				// Log.d(TAG, result.getAge().toString());
			}

			if (responseValue.has(Person.GENDER)) {
				result.setGender(responseValue.getString(Person.GENDER));
				// Log.d(TAG, result.getGender());
			}

			if (responseValue.has(Person.SMOKER)) {
				result.setSmoker(responseValue.getBoolean(Person.SMOKER));
				// Log.d(TAG, result.getSmoker().toString());
			}

			if (responseValue.has(Person.BLIND)) {
				result.setBlind(responseValue.getBoolean(Person.BLIND));
				// Log.d(TAG, result.getBlind().toString());
			}

			if (responseValue.has(Person.DEAF)) {
				result.setDeaf(responseValue.getBoolean(Person.DEAF));
				// Log.d(TAG, result.getDeaf().toString());
			}

			if (responseValue.has(Person.DOG)) {
				result.setDog(responseValue.getBoolean(Person.DOG));
				// Log.d(TAG, result.getDog().toString());
			}

			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static final List<Person> fetchPersons(JSONArray value)
			throws DycapoException {
		int size = value.length();
		List<Person> persons = new ArrayList<Person>();
		try {
			for (int i = 0; i < size; i++) {
				persons.add(PersonFetcher.fetchPerson(value.getJSONObject(i)));

			}
			return persons;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return persons;
	}
}
