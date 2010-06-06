/**
 * 
 */
package eu.fbk.dycapo.persistency;


/**
 * @author riccardo
 *
 */
public class Leg {
	private String mLeg;
	private int mDuration;

	/**
	 * @return the mLeg
	 */
	public String getmLeg() {
		return mLeg;
	}
	/**
	 * @param mLeg the mLeg to set
	 */
	public void setmLeg(String mLeg) {
		this.mLeg = mLeg;
	}
	/**
	 * @return the mDuration
	 */
	public int getmDuration() {
		return mDuration;
	}
	/**
	 * @param mDuration the mDuration to set
	 */
	public void setmDuration(int mDuration) {
		this.mDuration = mDuration;
	}
	/**
	 * @param mLeg
	 * @param mDuration
	 */
	public Leg(String mLeg, int mDuration) {
		this.mLeg = mLeg;
		this.mDuration = mDuration;
	}

}
