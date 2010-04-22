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
	
	private static final String AGE="age";
	private static final String NONSMOKING="nonsmoking";
	private static final String GENDER="gender";
	private static final String DRIVE="drive";
	private static final String RIDE="ride";
	
	private String age;				//may
	private boolean nonsmoking;		//may
	private boolean gender;			//may
	private boolean drive;			//may
	private boolean ride;			//may
	
	public Preferences(){
		
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
	public boolean isNonsmoking() {
		return nonsmoking;
	}

	/**
	 * @param nonsmoking the nonsmoking to set
	 */
	public void setNonsmoking(boolean nonsmoking) {
		this.nonsmoking = nonsmoking;
	}

	/**
	 * @return the gender
	 */
	public boolean getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(boolean gender) {
		this.gender = gender;
	}

	/**
	 * @return the drive
	 */
	public boolean isDrive() {
		return drive;
	}

	/**
	 * @param drive the drive to set
	 */
	public void setDrive(boolean drive) {
		this.drive = drive;
	}

	/**
	 * @return the ride
	 */
	public boolean isRide() {
		return ride;
	}

	/**
	 * @param ride the ride to set
	 */
	public void setRide(boolean ride) {
		this.ride = ride;
	}
	
	public HashMap<String,Object> toHashMap(){
		HashMap<String,Object> result= new HashMap<String,Object>();
		if (this.age instanceof java.lang.String)result.put(Preferences.AGE, this.age);
		result.put(Preferences.DRIVE, this.drive);
		result.put(Preferences.GENDER, this.gender);
		result.put(Preferences.NONSMOKING, this.nonsmoking);
		result.put(Preferences.RIDE, this.ride);
		return result;
	}
}
