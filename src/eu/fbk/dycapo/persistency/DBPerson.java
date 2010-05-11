/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.exceptions.Tag;
import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 *
 */
public final class DBPerson {
	public static boolean saveMe(Person person){
		ObjectContainer db=DBProvider.getDatabase();
		Person qbe = new Person();
		qbe.setUsername(person.getUsername());
		
		ObjectSet<Person> result = db.queryByExample(qbe);
		Person found;
		
		if (result.isEmpty()){
			
			found = new Person();
			found.setUsername(person.getUsername());
			ObjectSet<Person> test= db.queryByExample(Person.class);
			
			if (!test.isEmpty()){
				Person user= (Person)test.next();
				db.delete(Person.class);
				found.setBlind(user.isBlind());
				found.setSmoker(user.isSmoker());
				found.setDog(user.hasDog());
				found.setDeaf(user.isDeaf());
			}
		}
		else found = result.next();
		found.setPassword(person.getPassword());
		found.setGender(person.getGender());
		if (person.getIAge() instanceof Integer)found.setAge(person.getAge());
		if(person.getEmail() instanceof String)found.setEmail(person.getEmail());
		if(person.getFirst_name() instanceof String)found.setFirst_name(person.getFirst_name());
		if(person.getLast_name() instanceof String)found.setLast_name(person.getLast_name());
		if(person.getPhone() instanceof String)found.setPhone(person.getPhone());
		if(person.getUrl() instanceof String)found.setUrl(person.getUrl());
		db.store(found);
		db.commit();
		return true;
	}
	
	public static boolean savePersonalPrefs(Person person) throws DycapoException{
		ObjectContainer db = DBProvider.getDatabase();
		Person qbe = new Person();
		qbe.setUsername(person.getUsername());
		ObjectSet<Person> result= db.queryByExample(qbe);
		Person found = (Person) result.next();
		if(!(found instanceof Person))throw new DycapoException (Tag.LOG + "user does not exists");
		found.setBlind(person.isBlind());
		found.setDeaf(person.isDeaf());
		found.setDog(person.hasDog());
		found.setSmoker(person.isSmoker());
		db.store(found);
		db.commit();
		return true;
	}
	
	public static Person getUser(){
		ObjectContainer db = DBProvider.getDatabase();
		List<Person> user = db.queryByExample(Person.class);
		if (user.isEmpty())return null;
		return user.get(0);
	}
	
	public static Person updateMe(){
		return null;
	}
}
