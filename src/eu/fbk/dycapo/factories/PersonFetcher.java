/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.HashMap;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 *
 */
public abstract class PersonFetcher {
	@SuppressWarnings("unchecked")
	public static final Person fetchPerson(HashMap<String,Object> value) throws DycapoException{
		
			Person result = new Person();
			String message = "error PersonFetcher.fetchPerson : not enough parameters are given to define a person: missing ";
			
			//must
			//username
			if (value.containsKey(Person.USERNAME))
				result.setUsername((String)value.get(Person.USERNAME));
			else throw new DycapoException (message + Person.USERNAME);
			
			//may / should
			if (value.containsKey(Person.EMAIL))
				result.setEmail((String)value.get(Person.EMAIL));
			if (value.containsKey(Person.FIRST_NAME))
				result.setFirst_name((String)value.get(Person.FIRST_NAME));
			if (value.containsKey(Person.LAST_NAME))
				result.setLast_name((String)value.get(Person.LAST_NAME));
			if (value.containsKey(Person.URL))
				result.setUrl((String)value.get(Person.URL));
			if (value.containsKey(Person.PHONE))
				result.setPhone((String)value.get(Person.PHONE));
			if (value.containsKey(Person.POSITION))
				result.setPosition(LocationFetcher.fetchLocation(((HashMap<String,Object>)value.get(Person.POSITION))));
			if (value.containsKey(Person.AGE))
				result.setAge((Integer)value.get(Person.AGE));
			if (value.containsKey(Person.GENDER))
				result.setGender((String)value.get(Person.GENDER));
			if (value.containsKey(Person.SMOKER))
				result.setSmoker((Boolean)value.get(Person.SMOKER));
			if (value.containsKey(Person.BLIND))
				result.setBlind((Boolean)value.get(Person.BLIND));
			if (value.containsKey(Person.DEAF))
				result.setDeaf((Boolean)value.get(Person.DEAF));
			if (value.containsKey(Person.DOG))
				result.setDog((Boolean)value.get(Person.DOG));
			
			return result;

	}

	@SuppressWarnings("unchecked")
	public static final Person[] fetchPersons(Object[] value) throws DycapoException {
		
		int size = value.length;
		Person[] persons = new Person[size];
		for (int i = 0; i< size; i++ ){
			persons[i] = PersonFetcher.fetchPerson((HashMap<String,Object>)(value[i]));
		}
		return persons;
	}
}
