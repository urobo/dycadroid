/**
 * 
 */
package eu.fbk.dycapo.models;

import java.util.HashMap;

/**
 * @author riccardo
 *
 */
public class Person implements XMLRPCModel{
	
	private static final String USERNAME="username";
	private static final String EMAIL="email";
	private static final String PASSWORD="password";
	private static final String FIRST_NAME="first_name";
	private static final String LAST_NAME="last_name";
	private static final String URI="uri";
	private static final String PHONE="phone";
	private static final String POSITION="position";
	private static final String AGE="age";
	private static final String GENDER="gender";
	private static final String SMOKER="smoker";
	private static final String BLIND="blind";
	private static final String DEAF="deaf";
	private static final String DOG="dog";
	
	private String username;
	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private String uri;
	private String phone;
	private Location position;
	private int age;
	private String gender;
	private boolean smoker;
	private boolean blind;
	private boolean deaf;
	private boolean dog;
	
	/**
	 * 
	 */
	public Person() {
	}
	
	
	public Person(String username, String email, String password,
			String firstName, String lastName, int age, String gender,
			boolean smoker, boolean blind, boolean deaf, boolean dog) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.first_name = firstName;
		this.last_name = lastName;
		this.age = age;
		this.gender = gender;
		this.smoker = smoker;
		this.blind = blind;
		this.deaf = deaf;
		this.dog = dog;
	}
	


	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param uri
	 * @param phone
	 * @param position
	 * @param age
	 * @param gender
	 * @param smoker
	 * @param blind
	 * @param deaf
	 * @param dog
	 */
	public Person(String username, String email, String password,
			String firstName, String lastName, String uri, String phone,
			Location position, int age, String gender, boolean smoker,
			boolean blind, boolean deaf, boolean dog) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.first_name = firstName;
		this.last_name = lastName;
		this.uri = uri;
		this.phone = phone;
		this.position = position;
		this.age = age;
		this.gender = gender;
		this.smoker = smoker;
		this.blind = blind;
		this.deaf = deaf;
		this.dog = dog;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * @param firstName the first_name to set
	 */
	public void setFirst_name(String firstName) {
		first_name = firstName;
	}
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @param lastName the last_name to set
	 */
	public void setLast_name(String lastName) {
		last_name = lastName;
	}
	

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the position
	 */
	public Location getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(Location position) {
		this.position = position;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the smoker
	 */
	public boolean isSmoker() {
		return smoker;
	}
	/**
	 * @param smoker the smoker to set
	 */
	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}
	/**
	 * @return the blind
	 */
	public boolean isBlind() {
		return blind;
	}
	/**
	 * @param blind the blind to set
	 */
	public void setBlind(boolean blind) {
		this.blind = blind;
	}
	/**
	 * @return the deaf
	 */
	public boolean isDeaf() {
		return deaf;
	}
	/**
	 * @param deaf the deaf to set
	 */
	public void setDeaf(boolean deaf) {
		this.deaf = deaf;
	}
	/**
	 * @return the dog
	 */
	public boolean isDog() {
		return dog;
	}
	/**
	 * @param dog the dog to set
	 */
	public void setDog(boolean dog) {
		this.dog = dog;
	}


	@Override
	public HashMap<String, Object> toHashMap() {
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put(Person.AGE,this.age);
		result.put(Person.BLIND,this.blind);
		result.put(Person.DEAF,this.deaf);
		result.put(Person.DOG,this.dog);
		result.put(Person.EMAIL,this.email);
		result.put(Person.FIRST_NAME,this.first_name);
		result.put(Person.GENDER,this.gender);
		result.put(Person.LAST_NAME,this.last_name);
		result.put(Person.PASSWORD,this.password);
		result.put(Person.PHONE,this.phone);
		result.put(Person.POSITION,this.position);
		result.put(Person.SMOKER,this.smoker);
		result.put(Person.URI,this.uri);
		result.put(Person.USERNAME,this.username);
		return result;
	}
	
}
