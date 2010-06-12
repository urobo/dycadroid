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
public class Preferences implements XMLRPCModel {
	public static final String TAG = "Preferences";
	
	/**
	 * @return the nonsmoking
	 */
	public Boolean getNonsmoking() {
		return nonsmoking;
	}

	/**
	 * @param nonsmoking the nonsmoking to set
	 */
	public void setNonsmoking(Boolean nonsmoking) {
		this.nonsmoking = nonsmoking;
	}

	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return the drive
	 */
	public Boolean getDrive() {
		return drive;
	}

	/**
	 * @param drive the drive to set
	 */
	public void setDrive(Boolean drive) {
		this.drive = drive;
	}

	/**
	 * @return the ride
	 */
	public Boolean getRide() {
		return ride;
	}

	/**
	 * @param ride the ride to set
	 */
	public void setRide(Boolean ride) {
		this.ride = ride;
	}

	/**
	 * @return the pet
	 */
	public Boolean getPet() {
		return pet;
	}

	/**
	 * @param pet the pet to set
	 */
	public void setPet(Boolean pet) {
		this.pet = pet;
	}




	public static final String[] GENDER_PREFS={"male","female","both"};
	public static final String[] GENDER_TO={"m","f","b"};
	public static final int MALE=0;
	public static final int FEMALE=1;
	public static final int BOTH=2;
	
	public static final String AGE="age";
	public static final String NONSMOKING="nonsmoking";
	public static final String GENDER="gender";
	public static final String DRIVE="drive";
	public static final String RIDE="ride";
	public static final String PET="pet";
	
	private String age;				//may
	private Boolean nonsmoking;		//may
	private Integer gender;			//may
	private Boolean drive;			//may
	private Boolean ride;			//may
	private Boolean pet;			//may
	
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	public Preferences(){
		
	}
	
	/**
	 * @param age
	 * @param nonsmoking
	 * @param gender
	 * @param drive
	 * @param ride
	 */
	public Preferences(String age, Boolean nonsmoking, Integer gender,
			Boolean drive, Boolean ride) {
		this.age = age;
		this.nonsmoking = nonsmoking;
		this.gender = gender;
		this.drive = drive;
		this.ride = ride;
	}



	
	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result= new HashMap<String,Object>();
		if (this.age instanceof java.lang.String)result.put(Preferences.AGE, this.age);
		if (this.drive instanceof java.lang.Boolean)result.put(Preferences.DRIVE, this.drive);
		if (this.gender instanceof java.lang.Integer)
			result.put(Preferences.GENDER, Preferences.GENDER_TO[this.gender]);
		if (this.nonsmoking instanceof java.lang.Boolean)result.put(Preferences.NONSMOKING, this.nonsmoking);
		if (this.ride instanceof java.lang.Boolean)result.put(Preferences.RIDE, this.ride);
		if (this.pet instanceof Boolean) result.put(Preferences.PET, this.pet);
		return result;
	}

//	@Override
//	public void fromBundle(Bundle data) {
//	
//		if (data.containsKey(Preferences.AGE))
//			this.age = data.getString(Preferences.AGE);
//		
//		if (data.containsKey(Preferences.DRIVE))
//			this.drive = data.getBoolean(Preferences.DRIVE);
//		
//		if (data.containsKey(Preferences.GENDER))
//			this.gender = data.getInt(Preferences.GENDER);
//		
//		if (data.containsKey(Preferences.NONSMOKING))
//			this.nonsmoking = data.getBoolean(Preferences.NONSMOKING);
//		
//		if (data.containsKey(Preferences.PET))
//			this.pet = data.getBoolean(Preferences.PET);
//		
//		if (data.containsKey(Preferences.RIDE))
//			this.ride = data.getBoolean(Preferences.RIDE);
//	}
//
//	@Override
//	public Bundle toBundle() {
//		Bundle result = new Bundle();
//		
//		if (this.age instanceof String)
//			result.putString(Preferences.AGE, this.age);
//		
//		if (this.drive instanceof Boolean)
//			result.putBoolean(Preferences.DRIVE, this.drive);
//		
//		if (this.gender instanceof Integer)
//			result.putInt(Preferences.GENDER, this.gender);
//		
//		if (this.nonsmoking instanceof Boolean)
//			result.putBoolean(Preferences.NONSMOKING, this.nonsmoking);
//		
//		if (this.pet instanceof Boolean)
//			result.putBoolean(Preferences.PET, this.pet);
//		
//		if (this.ride instanceof Boolean)
//			result.putBoolean(Preferences.RIDE, this.ride);
//		
//		return result;
//	}
}
