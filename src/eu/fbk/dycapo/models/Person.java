/**
 * 
 */
package eu.fbk.dycapo.models;

import java.util.HashMap;

import eu.fbk.dycapo.xmlrpc.XMLRPCModel;

/**
 * @author riccardo
 *
 */
public class Person implements XMLRPCModel{
	public static final String TAG = "Person";
	
	public static final String USERNAME="username";
	public static final String EMAIL="email";
	public static final String FIRST_NAME="first_name";
	public static final String LAST_NAME="last_name";
	public static final String URL="url";
	public static final String PHONE="phone";
	public static final String POSITION="position";
	public static final String AGE="age";
	public static final String GENDER="gender";
	public static final String SMOKER="smoker";
	public static final String BLIND="blind";
	public static final String DEAF="deaf";
	public static final String DOG="dog";
	
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


	@Override
	public HashMap<String, Object> toHashMap() {
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (this.age > 0 && this.age < 100)result.put(Person.AGE,this.age);
		if (this.blind instanceof Boolean)result.put(Person.BLIND,this.blind);
		if (this.deaf instanceof Boolean)result.put(Person.DEAF,this.deaf);
		if (this.dog instanceof Boolean)result.put(Person.DOG,this.dog);
		if (this.email instanceof java.lang.String)result.put(Person.EMAIL,this.email);
		if (this.first_name instanceof java.lang.String)result.put(Person.FIRST_NAME,this.first_name);
		if (this.gender instanceof java.lang.String)result.put(Person.GENDER,this.gender);
		if (this.last_name instanceof java.lang.String)result.put(Person.LAST_NAME,this.last_name);
		if (this.phone instanceof java.lang.String)result.put(Person.PHONE,this.phone);
		if (this.position instanceof Location)result.put(Person.POSITION,this.position.toHashMap());
		if (this.smoker instanceof Boolean)result.put(Person.SMOKER,this.smoker);
		if (this.url instanceof java.lang.String)result.put(Person.URL,this.url);
		if (this.username instanceof java.lang.String)result.put(Person.USERNAME,this.username);
		return result;
	}


//	@Override
//	public void fromBundle(Bundle data) {
//		
//		if (data.containsKey(Person.AGE))
//			this.age = data.getInt(Person.AGE);
//		
//		if (data.containsKey(Person.BLIND))
//			this.blind = data.getBoolean(Person.BLIND);
//		
//		if (data.containsKey(Person.DEAF))
//			this.deaf = data.getBoolean(Person.DEAF);
//		
//		if (data.containsKey(Person.DOG))
//			this.dog = data.getBoolean(Person.DOG);
//		
//		if (data.containsKey(Person.EMAIL))
//			this.email = data.getString(Person.EMAIL);
//		
//		if (data.containsKey(Person.FIRST_NAME))
//			this.first_name = data.getString(Person.FIRST_NAME);
//		
//		if (data.containsKey(Person.GENDER))
//			this.gender = data.getString(Person.GENDER);
//		
//		if (data.containsKey(Person.LAST_NAME))
//			this.last_name = data.getString(Person.LAST_NAME);
//		
//		if (data.containsKey(Person.PHONE))
//			this.phone = data.getString(Person.PHONE);
//		
//		if (data.containsKey(Person.POSITION))
//			this.position.fromBundle(data.getBundle(Person.POSITION));
//		
//		if (data.containsKey(Person.SMOKER))
//			this.smoker = data.getBoolean(Person.SMOKER);
//		
//		if (data.containsKey(Person.URL))
//			this.url = data.getString(Person.URL);
//		
//		if (data.containsKey(Person.USERNAME))
//			this.username = data.getString(Person.USERNAME);
//		
//	}
//
//
//	@Override
//	public Bundle toBundle() {
//		Bundle result = new Bundle();
//		
//		if (this.age instanceof Integer)
//			result.putInt(Person.AGE, this.age);
//		
//		if (this.blind instanceof Boolean)
//			result.putBoolean(Person.BLIND, this.blind);
//		
//		if (this.deaf instanceof Boolean)
//			result.putBoolean(Person.DEAF, this.deaf);
//		
//		if (this.dog instanceof Boolean)
//			result.putBoolean(Person.DOG, this.dog);
//		
//		if (this.email instanceof String)
//			result.putString(Person.EMAIL, this.email);
//		
//		if (this.first_name instanceof String)
//			result.putString(Person.FIRST_NAME, this.first_name);
//		
//		if (this.gender instanceof String)
//			result.putString(Person.GENDER, this.gender);
//		
//		if (this.last_name instanceof String)
//			result.putString(Person.LAST_NAME, this.last_name);
//		
//		if (this.phone instanceof String)
//			result.putString(Person.PHONE, this.phone);
//		
//		if (this.position instanceof Location)
//			result.putBundle(Person.POSITION, this.position.toBundle());
//		
//		if (this.url instanceof String)
//			result.putString(Person.URL, this.url);
//		
//		if (this.username instanceof String)
//			result.putString(Person.USERNAME, this.username);
//		
//		return result;
//	}
	
}
