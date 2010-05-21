/**
 * 
 */
package eu.fbk.dycapo.persistency;

import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 *
 */
public class User extends Person {
	private String password;
	
	
	
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param smoker
	 * @param blind
	 * @param deaf
	 * @param dog
	 */
	public User(String username, String email, String password,
			String firstName, String lastName, int age, String gender,
			boolean smoker, boolean blind, boolean deaf, boolean dog) {
		super(username, email, firstName, lastName, age, gender, smoker,
				blind, deaf, dog);
		this.password=password;
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param url
	 * @param phone
	 * @param position
	 * @param age
	 * @param gender
	 * @param smoker
	 * @param blind
	 * @param deaf
	 * @param dog
	 */
	public User(String username, String email, String password,
			String firstName, String lastName, String url, String phone,
			Location position, int age, String gender, boolean smoker,
			boolean blind, boolean deaf, boolean dog) {
		super(username, email, firstName, lastName, url, phone, position,
				age, gender, smoker, blind, deaf, dog);
		this.password=password;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}		
}
