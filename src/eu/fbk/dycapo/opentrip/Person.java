/**
 * 
 */
package eu.fbk.dycapo.opentrip;

/**
 * @author riccardo
 *
 */
public class Person {
	private String name;
	private String userid;
	private String email;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
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
