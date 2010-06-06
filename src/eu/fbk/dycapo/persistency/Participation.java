/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.Date;

import eu.fbk.dycapo.models.Person;

/**
 * @author riccardo
 *
 */
public class Participation {
	public static final String PARTICIPANT = "mParticipant";
	
	
	private String mRole;
	private Person mParticipant;
	private Date mTime;
	private Date mSarted;
	private Date mFinished;
	
	/**
	 * @param mRole
	 * @param mParticipant
	 * @param mTime
	 */
	public Participation(String mRole, Person mParticipant, Date mTime) {
		
		this.mRole = mRole;
		this.mParticipant = mParticipant;
		this.mTime = mTime;
	}
	/**
	 * @return the mRole
	 */
	public String getmRole() {
		return mRole;
	}
	/**
	 * @param mRole the mRole to set
	 */
	public void setmRole(String mRole) {
		this.mRole = mRole;
	}
	/**
	 * @return the mParticipant
	 */
	public Person getmParticipant() {
		return mParticipant;
	}
	/**
	 * @param mParticipant the mParticipant to set
	 */
	public void setmParticipant(Person mParticipant) {
		this.mParticipant = mParticipant;
	}
	/**
	 * @return the mTime
	 */
	public Date getmTime() {
		return mTime;
	}
	/**
	 * @param mTime the mTime to set
	 */
	public void setmTime(Date mTime) {
		this.mTime = mTime;
	}
	/**
	 * @return the mSarted
	 */
	public Date getmSarted() {
		return mSarted;
	}
	/**
	 * @param mSarted the mSarted to set
	 */
	public void setmSarted(Date mSarted) {
		this.mSarted = mSarted;
	}
	/**
	 * @return the mFinished
	 */
	public Date getmFinished() {
		return mFinished;
	}
	/**
	 * @param mFinished the mFinished to set
	 */
	public void setmFinished(Date mFinished) {
		this.mFinished = mFinished;
	}
	
}
