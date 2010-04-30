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
	
	public static final String AGE="age";
	public static final String NONSMOKING="nonsmoking";
	public static final String GENDER="gender";
	public static final String DRIVE="drive";
	public static final String RIDE="ride";
	
	private String age;				//may
	private Boolean nonsmoking;		//may
	private Boolean gender;			//may
	private Boolean drive;			//may
	private Boolean ride;			//may
	
	public Preferences(){
		
	}
	
	/**
	 * @param age
	 * @param nonsmoking
	 * @param gender
	 * @param drive
	 * @param ride
	 */
	public Preferences(String age, Boolean nonsmoking, Boolean gender,
			Boolean drive, Boolean ride) {
		this.age = age;
		this.nonsmoking = nonsmoking;
		this.gender = gender;
		this.drive = drive;
		this.ride = ride;
	}

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
	public Boolean getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(Boolean gender) {
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




	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result= new HashMap<String,Object>();
		if (this.age instanceof java.lang.String)result.put(Preferences.AGE, this.age);
		if (this.drive instanceof java.lang.Boolean)result.put(Preferences.DRIVE, this.drive);
		if (this.gender instanceof java.lang.Boolean)result.put(Preferences.GENDER, this.gender);
		if (this.nonsmoking instanceof java.lang.Boolean)result.put(Preferences.NONSMOKING, this.nonsmoking);
		if (this.ride instanceof java.lang.Boolean)result.put(Preferences.RIDE, this.ride);
		return result;
	}
}
