/**
 * 
 */
package eu.fbk.dycapo.models;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import eu.fbk.dycapo.factories.json.DycapoJSONObjects;

/**
 * @author riccardo
 *
 */
public class Person implements DycapoJSONObjects{
	public static final String TAG = "Person";
	public static final String USERNAME="username";
	public static final String EMAIL="email";
	public static final String FIRST_NAME="first_name";
	public static final String LAST_NAME="last_name";
	public static final String URL="url";
	public static final String PHONE="phone";
	public static final String POSITION="location";
	public static final String AGE="age";
	public static final String GENDER="gender";
	public static final String SMOKER="smoker";
	public static final String BLIND="blind";
	public static final String DEAF="deaf";
	public static final String DOG="dog";
	
	public static final String MALE = "M";
	public static final String FEMALE = "F";
	public static final String HREF = "href";
	
	private String username;	//must
	private String email;		//may	
	private String first_name;	//should
	private String last_name;	//should
	private String url;			//may
	private String phone;		//should
	private Location position;	//may
	private Integer age;			//should
	private String gender;		//should
	private Boolean smoker;		//may
	private Boolean blind;		//should
	private Boolean deaf;		//should
	private Boolean dog;		//should
	protected String href;
		
	/**
	 * 
	 */
	public Person() {
	}
	
	
	public Person(String username, String email, String firstName, String lastName, int age, String gender,
			boolean smoker, boolean blind, boolean deaf, boolean dog) {
		this.username = username;
		this.email = email;
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
	public Person(String username, String email,
			String firstName, String lastName, String url, String phone,
			Location position, int age, String gender, boolean smoker,
			boolean blind, boolean deaf, boolean dog) {
		this.username = username;
		this.email = email;
		this.first_name = firstName;
		this.last_name = lastName;
		this.url = url;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	public Integer getAge() {
		return age;
	}


	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
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
	public Boolean getSmoker() {
		return smoker;
	}


	/**
	 * @param smoker the smoker to set
	 */
	public void setSmoker(Boolean smoker) {
		this.smoker = smoker;
	}


	/**
	 * @return the blind
	 */
	public Boolean getBlind() {
		return blind;
	}


	/**
	 * @param blind the blind to set
	 */
	public void setBlind(Boolean blind) {
		this.blind = blind;
	}


	/**
	 * @return the deaf
	 */
	public Boolean getDeaf() {
		return deaf;
	}


	/**
	 * @param deaf the deaf to set
	 */
	public void setDeaf(Boolean deaf) {
		this.deaf = deaf;
	}


	/**
	 * @return the dog
	 */
	public Boolean getDog() {
		return dog;
	}


	/**
	 * @param dog the dog to set
	 */
	public void setDog(Boolean dog) {
		this.dog = dog;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}


	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject result = new JSONObject();
		
			try {
				if (this.age instanceof Integer)
					result.put(Person.AGE, this.age);
				if (this.blind instanceof Boolean)
					result.put(Person.BLIND, this.blind.booleanValue());
				if (this.deaf instanceof Boolean)
					result.put(Person.DEAF, this.deaf.booleanValue());
				if (this.dog instanceof Boolean)
					result.put(Person.DOG, this.dog.booleanValue());
				if (this.email instanceof java.lang.String)
					result.put(Person.EMAIL, this.email);
				if (this.first_name instanceof java.lang.String)
					result.put(Person.FIRST_NAME, this.first_name);
				if (this.gender instanceof java.lang.String)
					result.put(Person.GENDER, this.gender);
				if (this.last_name instanceof java.lang.String)
					result.put(Person.LAST_NAME, this.last_name);
				if (this.phone instanceof java.lang.String)
					result.put(Person.PHONE, this.phone);
				if (this.position instanceof Location)
					result.put(Person.POSITION, this.position.toJSONObject());
				if (this.smoker instanceof Boolean)
					result.put(Person.SMOKER, this.smoker.booleanValue());
				if (this.url instanceof java.lang.String)
					result.put(Person.URL, this.url);
				if (this.username instanceof java.lang.String)
					result.put(Person.USERNAME, this.username);
				
				Log.d(TAG,result.toString());
				return result;	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		return null;
	}


}
