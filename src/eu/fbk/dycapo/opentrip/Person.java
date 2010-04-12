/**
 * 
 */
package eu.fbk.dycapo.opentrip;

import java.util.Date;



/**
 * @author riccardo
 *
 */
public class Person {
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
	 * @return the last_login
	 */
	public Date getLast_login() {
		return last_login;
	}
	/**
	 * @param lastLogin the last_login to set
	 */
	public void setLast_login(Date lastLogin) {
		last_login = lastLogin;
	}
	/**
	 * @return the date_joined
	 */
	public Date getDate_joined() {
		return date_joined;
	}
	/**
	 * @param dateJoined the date_joined to set
	 */
	public void setDate_joined(Date dateJoined) {
		date_joined = dateJoined;
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
	
}
