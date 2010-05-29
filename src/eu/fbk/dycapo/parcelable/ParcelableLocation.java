/**
 * 
 */
package eu.fbk.dycapo.parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import eu.fbk.dycapo.models.Location;

/**
 * @author riccardo
 *
 */
public class ParcelableLocation extends Location implements Parcelable, BundleModel{
	private static final String TAG = "ParcelableLocation";
	
	/**
	 * 
	 */
	public ParcelableLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param point
	 * @param georssPoint
	 * @param offset
	 * @param leaves
	 */
	public ParcelableLocation(String label, int point, String georssPoint,
			int offset, Date leaves) {
		super(label, point, georssPoint, offset, leaves);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	public ParcelableLocation(Parcel in){
		this.readFromParcel(in);
	}
	
	private void readFromParcel(Parcel in) {
		this.fromBundle(in.readBundle());		
	}

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
		dest.writeBundle(this.toBundle());
	}

	@Override
	public Bundle toBundle() {
		Bundle result = new Bundle();
		if(this.country instanceof String) result.putString(Location.COUNTRY, this.country);
		if(this.days instanceof String) result.putString(Location.DAYS, this.days);
		if(this.georss_point instanceof String) result.putString(Location.GEORSS_POINT, this.georss_point);
		if(this.label instanceof String) result.putString(Location.LABEL, this.label);
		if(this.leaves instanceof Date) {
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			result.putString(Location.LEAVES, formatter.format(this.leaves));
		}
		if(this.offset instanceof Integer) result.putInt(Location.OFFSET, this.offset);
		if(this.point instanceof Integer) result.putString(Location.POINT, Location.POINT_TYPE[this.point]);
		if(this.postcode instanceof Integer) result.putInt(Location.POSTCODE, this.postcode);
		if(this.recurs instanceof String) result.putString(Location.RECURS, this.recurs);
		if(this.region instanceof String) result.putString(Location.REGION, this.region);
		if(this.street instanceof String) result.putString(Location.STREET, this.street);
		if(this.subregion instanceof String) result.putString(Location.SUBREGION, this.subregion);
		if(this.town instanceof String) result.putString(Location.TOWN, this.town);
		return result;
	}

	@Override
	public void fromBundle(Bundle data) {
		
		if(data.containsKey(Location.COUNTRY))
			this.country = data.getString(Location.COUNTRY);
		
		if(data.containsKey(Location.DAYS))
			this.days = data.getString(Location.DAYS);
		
		if(data.containsKey(Location.GEORSS_POINT))
			this.georss_point = data.getString(Location.GEORSS_POINT);
		
		if(data.containsKey(Location.LABEL))
			this.label = data.getString(Location.LABEL);
		
		if(data.containsKey(Location.LEAVES)){
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			try {
				this.leaves = sdf.parse(data.getString(Location.LEAVES));
			} catch (ParseException e) {
				Log.d(TAG, e.getMessage());
			}
		}
		
		if(data.containsKey(Location.OFFSET))
			this.offset = data.getInt(Location.OFFSET);
		
		if(data.containsKey(Location.POINT)){
			String point_type = data.getString(Location.POINT);
			if (point_type.equals(Location.POINT_TYPE[Location.ORIG]))
				this.point = Location.ORIG;
			else if (point_type.equals(Location.POINT_TYPE[Location.DEST]))
				this.point = Location.DEST;
			else if (point_type.equals(Location.POINT_TYPE[Location.WAYP]))
				this.point = Location.WAYP;
			else if (point_type.equals(Location.POINT_TYPE[Location.POSI]))
				this.point = Location.POSI;
		}
		
		if(data.containsKey(Location.POSTCODE))
			this.postcode = data.getInt(Location.POSTCODE);
		
		if(data.containsKey(Location.RECURS))
			this.recurs = data.getString(Location.RECURS);
		
		if(data.containsKey(Location.REGION))
			this.region = data.getString(Location.REGION);
		
		if(data.containsKey(Location.STREET))
			this.street = data.getString(Location.STREET);
		
		if(data.containsKey(Location.SUBREGION))
			this.subregion = data.getString(Location.SUBREGION);
		
		if(data.containsKey(Location.TOWN))
			this.town = data.getString(Location.TOWN);
		
	}

}
