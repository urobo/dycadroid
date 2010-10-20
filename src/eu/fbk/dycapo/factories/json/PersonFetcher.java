/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.persistency.DBPerson;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.services.DycapoServiceClient;

/**
 * @author riccardo
 *
 */
public abstract class PersonFetcher {
	public static final Person fetchPerson(JSONObject responseValue) throws DycapoException{
		
		Person result = new Person();
		String message = "error PersonFetcher.fetchPerson : not enough parameters are given to define a person: missing ";
		try{
			
			if (responseValue.has(DycapoObjectsFetcher.HREF))
				result.setHref(responseValue.getString(DycapoObjectsFetcher.HREF));
			
			//must
			//username
			if (responseValue.has(Person.USERNAME))
				result.setUsername(responseValue.getString(Person.USERNAME));
			else throw new DycapoException (message + Person.USERNAME);
			
			//may / should
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
			if (responseValue.has(Person.POSITION))
				if(responseValue.getJSONObject(Person.POSITION).has(DycapoObjectsFetcher.HREF)){
					User usr = DBPerson.getUser();
					String url = responseValue.getJSONObject(Person.POSITION).getString(DycapoObjectsFetcher.HREF);
					JSONObject json= DycapoServiceClient.callDycapo(DycapoServiceClient.GET, 
							url, 
							null, 
							usr.getUsername(),
							usr.getPassword());
					
					result.setPosition(LocationFetcher.fetchLocation(json));
				}
			
			
			if (responseValue.has(Person.AGE))
				result.setAge(responseValue.getInt(Person.AGE));
			if (responseValue.has(Person.GENDER))
				result.setGender(responseValue.getString(Person.GENDER));
			if (responseValue.has(Person.SMOKER))
				result.setSmoker(responseValue.getBoolean(Person.SMOKER));
			if (responseValue.has(Person.BLIND))
				result.setBlind(responseValue.getBoolean(Person.BLIND));
			if (responseValue.has(Person.DEAF))
				result.setDeaf(responseValue.getBoolean(Person.DEAF));
			if (responseValue.has(Person.DOG))
				result.setDog(responseValue.getBoolean(Person.DOG));
			
			return result;
		}catch(JSONException e){
			e.printStackTrace();
		}
		return null;
	}

	public static final List<Person> fetchPersons(JSONArray value) throws DycapoException {
		int size = value.length();
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i< size; i++ ){
			try {
				persons.add(PersonFetcher.fetchPerson(value.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return persons;
	}
}
