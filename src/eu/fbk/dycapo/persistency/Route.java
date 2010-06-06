/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.ArrayList;
import java.util.List;

/**
 * @author riccardo
 *
 */
public class Route {
	private String mDirections;
	private Integer mDurationSecs;
	private List<Leg> mLegs;
	private String mStatus;
	private String mPolyline;
	/**
	 * @return the mDiretions
	 */
	public String getmDirections() {
		return mDirections;
	}
	/**
	 * @param mDiretions the mDiretions to set
	 */
	public void setmDirections(String mDiretions) {
		this.mDirections = mDiretions;
	}
	/**
	 * @return the mDurationSecs
	 */
	public Integer getmDurationSecs() {
		return mDurationSecs;
	}
	/**
	 * @param mDurationSecs the mDurationSecs to set
	 */
	public void setmDurationSecs(Integer mDurationSecs) {
		this.mDurationSecs = mDurationSecs;
	}
	/**
	 * @return the legs
	 */
	public List<Leg> getLegs() {
		return mLegs;
	}
	/**
	 * @param legs the legs to set
	 */
	public void setLegs(ArrayList<Leg> legs) {
		this.mLegs = legs;
	}
	/**
	 * @return the mStatus
	 */
	public String getmStatus() {
		return mStatus;
	}
	/**
	 * @param mStatus the mStatus to set
	 */
	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}
	/**
	 * @return the mPolyline
	 */
	public String getmPolyline() {
		return mPolyline;
	}
	/**
	 * @param mPolyline the mPolyline to set
	 */
	public void setmPolyline(String mPolyline) {
		this.mPolyline = mPolyline;
	}
	
	public void addLeg(Leg leg){
		this.mLegs.add(leg);
	}
	
}
