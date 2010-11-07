/**
 * 
 */
package eu.fbk.dycapo.persistency;

import java.util.List;

import com.google.android.maps.GeoPoint;

/**
 * @author riccardo
 * 
 */
public class Route {
	private List<GeoPoint> mDecodedPolyline;
	private Integer mDurationSecs;
	private String mStatusCode;

	/**
	 * @return the mDecodedPolyline
	 */
	public List<GeoPoint> getmDecodedPolyline() {
		return mDecodedPolyline;
	}

	/**
	 * @param mDecodedPolyline
	 *            the mDecodedPolyline to set
	 */
	public void setmDecodedPolyline(List<GeoPoint> mDecodedPolyline) {
		this.mDecodedPolyline = mDecodedPolyline;
	}

	/**
	 * @return the mDurationSecs
	 */
	public Integer getmDurationSecs() {
		return mDurationSecs;
	}

	/**
	 * @param mDurationSecs
	 *            the mDurationSecs to set
	 */
	public void setmDurationSecs(Integer mDurationSecs) {
		this.mDurationSecs = mDurationSecs;
	}

	/**
	 * @return the mStatusCode
	 */
	public String getmStatusCode() {
		return mStatusCode;
	}

	/**
	 * @param mStatusCode
	 *            the mStatusCode to set
	 */
	public void setmStatusCode(String mStatusCode) {
		this.mStatusCode = mStatusCode;
	}

}
