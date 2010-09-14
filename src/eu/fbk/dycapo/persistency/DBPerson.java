/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Location;

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
		if (person.getPassword() instanceof String)found.setPassword(person.getPassword());
		if (person.getGender() instanceof String)found.setGender(person.getGender());
		if (person.getAge() instanceof Integer)found.setAge(person.getAge());
		if(person.getEmail() instanceof String)found.setEmail(person.getEmail());
		if(person.getFirst_name() instanceof String)found.setFirst_name(person.getFirst_name());
		if(person.getLast_name() instanceof String)found.setLast_name(person.getLast_name());
		if(person.getPhone() instanceof String)found.setPhone(person.getPhone());
		if(person.getUrl() instanceof String)found.setUrl(person.getUrl());
		if(person.getHref() instanceof String)found.setHref(person.getHref());
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
	
	public static boolean updateMe(User user){
		ObjectContainer db = DBProvider.getDatabase();
		List<User> usr = db.queryByExample(User.class);
		if (!usr.isEmpty()){
			User dbuser= usr.get(0);
			if (user.getAge() instanceof Integer)
				dbuser.setAge(user.getAge());
			
			if (user.getEmail() instanceof String)
				dbuser.setEmail(user.getEmail());
			
			if (user.getFirst_name() instanceof String)
				dbuser.setFirst_name(user.getFirst_name());
			
			if (user.getGender() instanceof String)
				dbuser.setGender(user.getGender());
			
			if (user.getLast_name()instanceof String)
				dbuser.setLast_name(user.getLast_name());
			
			if (user.getPassword() instanceof String)
				dbuser.setPassword(user.getPassword());
			
			if (user.getPhone() instanceof String)
				dbuser.setPhone(user.getPhone());
			
			if (user.getPosition() instanceof Location)
				dbuser.setPosition(user.getPosition());
			
			if (user.getUrl() instanceof String)
				dbuser.setUrl(user.getUrl());
			
			if (user.getUsername() instanceof String)
				dbuser.setUsername(user.getUsername());
			
			db.delete(User.class);
			db.store(dbuser);
			db.commit();
		
			return true;
		}
		else return false;
	}
}
