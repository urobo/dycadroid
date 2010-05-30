/**
 * 
 */
package eu.fbk.dycapo.bundles;

import android.os.Bundle;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 *
 */
public final class PersonBundle {
	public static Bundle toBundle (Person person){
		Bundle result = new Bundle();
		
		if (person.getAge() instanceof Integer)
			result.putInt(Person.AGE, person.getAge());
		
		if (person.getBlind() instanceof Boolean)
			result.putBoolean(Person.BLIND, person.getBlind());
		
		if (person.getDeaf() instanceof Boolean)
			result.putBoolean(Person.DEAF, person.getDeaf());
		
		if (person.getDeaf() instanceof Boolean)
			result.putBoolean(Person.DOG, person.getDeaf());
		
		if (person.getEmail() instanceof String)
			result.putString(Person.EMAIL, person.getEmail());
		
		if (person.getFirst_name() instanceof String)
			result.putString(Person.FIRST_NAME, person.getFirst_name());
		
		if (person.getGender() instanceof String)
			result.putString(Person.GENDER, person.getGender());
		
		if (person.getLast_name() instanceof String)
			result.putString(Person.LAST_NAME, person.getLast_name());
		
		if (person.getPhone() instanceof String)
			result.putString(Person.PHONE, person.getPhone());
		
		if (person.getPosition() instanceof Location)
			result.putBundle(Person.POSITION, LocationBundle.toBundle(person.getPosition()));
		
		if (person.getUrl() instanceof String)
			result.putString(Person.URL, person.getUrl());
		
		if (person.getUsername() instanceof String)
			result.putString(Person.USERNAME, person.getUsername());
		
		return result;
	}
	
	public static Person fromBundle (Bundle data){
		Person result = new Person ();
		
		if (data.containsKey(Person.AGE))
			result.setAge(data.getInt(Person.AGE));
		
		if (data.containsKey(Person.BLIND))
			result.setBlind(data.getBoolean(Person.BLIND));
		
		if (data.containsKey(Person.DEAF))
			result.setDeaf(data.getBoolean(Person.DEAF));
		
		if (data.containsKey(Person.DOG))
			result.setDog(data.getBoolean(Person.DOG));
		
		if (data.containsKey(Person.EMAIL))
			result.setEmail(data.getString(Person.EMAIL));
		
		if (data.containsKey(Person.FIRST_NAME))
			result.setFirst_name(data.getString(Person.FIRST_NAME));
		
		if (data.containsKey(Person.GENDER))
			result.setGender(data.getString(Person.GENDER));
		
		if (data.containsKey(Person.LAST_NAME))
			result.setLast_name(data.getString(Person.LAST_NAME));
		
		if (data.containsKey(Person.PHONE))
			result.setPhone(data.getString(Person.PHONE));
		
		if (data.containsKey(Person.POSITION))
			result.setPosition(LocationBundle.fromBundle(data.getBundle(Person.POSITION)));
		
		if (data.containsKey(Person.SMOKER))
			result.setSmoker(data.getBoolean(Person.SMOKER));
		
		if (data.containsKey(Person.URL))
			result.setUrl(data.getString(Person.URL));
		
		if (data.containsKey(Person.USERNAME))
			result.setUsername(data.getString(Person.USERNAME));
		
		return result;
	}
}
