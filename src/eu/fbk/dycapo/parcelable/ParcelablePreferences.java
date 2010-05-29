/**
 * 
 */
package eu.fbk.dycapo.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import eu.fbk.dycapo.models.Preferences;

/**
 * @author riccardo
 *
 */
public class ParcelablePreferences extends Preferences implements Parcelable {

	/* (non-Javadoc)
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

}
