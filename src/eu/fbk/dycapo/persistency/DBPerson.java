/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 *
 */
public final class DBPerson {
	public static boolean saveMe(User person){
		ObjectContainer db=DBProvider.getDatabase();
		
		
		ObjectSet<User> result = db.queryByExample(User.class);
		User found;
		
		if (result.isEmpty()){
			found = new User();
			found.setUsername(person.getUsername());
		}
		else {
			found = result.next();
		    db.delete(User.class);
		}
		
		//check only different params
		found.setPassword(person.getPassword());
		found.setGender(person.getGender());
		if (person.getAge() instanceof Integer)found.setAge(person.getAge());
		if(person.getEmail() instanceof String)found.setEmail(person.getEmail());
		if(person.getFirst_name() instanceof String)found.setFirst_name(person.getFirst_name());
		if(person.getLast_name() instanceof String)found.setLast_name(person.getLast_name());
		if(person.getPhone() instanceof String)found.setPhone(person.getPhone());
		if(person.getUrl() instanceof String)found.setUrl(person.getUrl());
		db.store(found);
		db.commit();
		return true;
	}
	
	public static boolean savePersonalPrefs(User person) throws DycapoException{
		ObjectContainer db = DBProvider.getDatabase();

		ObjectSet<User> result= db.queryByExample(User.class);
		if (!result.isEmpty()){
			User found = result.next();
			found.setBlind(person.getBlind());
			found.setDeaf(person.getDeaf());
			found.setDog(person.getDog());
			found.setSmoker(person.getSmoker());
			db.delete(User.class);
			db.store(found);
			db.commit();
			return true;
		}return false;
	}
	
	public static User getUser(){
		ObjectContainer db = DBProvider.getDatabase();
		List<User> user = db.queryByExample(User.class);
		if (user.isEmpty())return null;
		return user.get(0);
	}
	
	public static Person updateMe(){
		return null;
	}
}
