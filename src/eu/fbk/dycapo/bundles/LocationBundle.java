/**
 * 
 */
package eu.fbk.dycapo.bundles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.util.Log;
import eu.fbk.dycapo.models.Location;

/**
 * @author riccardo
 *
 */
public abstract class LocationBundle {
	public static final String TAG = "LocationBundle";
	
	public static final Bundle toBundle (Location location){
		
		Bundle result = new Bundle();
		
		if(location.getHref() instanceof String)
			result.putString(Location.HREF, location.getHref());
		
		if(location.getCountry() instanceof String) 
			result.putString(Location.COUNTRY, location.getCountry());
		
		if(location.getDays() instanceof String) 
			result.putString(Location.DAYS, location.getDays());
		
		if(location.getGeorss_point() instanceof String) 
			result.putString(Location.GEORSS_POINT, location.getGeorss_point());
		
		if(location.getLabel() instanceof String) 
			result.putString(Location.LABEL, location.getLabel());
		
		if(location.getLeaves() instanceof Date) {
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			result.putString(Location.LEAVES, formatter.format(location.getLeaves()));
		}
		
		if(location.getOffset() instanceof Integer) 
			result.putInt(Location.OFFSET, location.getOffset());
		
		if(location.getPoint() instanceof Integer) 
			result.putString(Location.POINT, Location.POINT_TYPE[location.getPoint()]);
		
		if(location.getPostcode() instanceof Integer) 
			result.putInt(Location.POSTCODE, location.getPostcode());
		
		if(location.getRecurs() instanceof String) 
			result.putString(Location.RECURS, location.getRecurs());
		
		if(location.getRegion() instanceof String) 
			result.putString(Location.REGION, location.getRegion());
		
		if(location.getStreet() instanceof String) 
			result.putString(Location.STREET, location.getStreet());
		
		if(location.getSubregion() instanceof String) 
			result.putString(Location.SUBREGION, location.getSubregion());
		
		if(location.getTown() instanceof String) 
			result.putString(Location.TOWN, location.getTown());
		
		return result;
	}
	
	public static final Location fromBundle (Bundle data){
		
		Location location = new Location ();
		
		if(data.containsKey(Location.HREF))
			location.setHref(data.getString(Location.HREF));
		
		if(data.containsKey(Location.COUNTRY))
			location.setCountry(data.getString(Location.COUNTRY));
		
		if(data.containsKey(Location.DAYS))
			location.setDays(data.getString(Location.DAYS));
		
		if(data.containsKey(Location.GEORSS_POINT))
			location.setGeorss_point(data.getString(Location.GEORSS_POINT));
		
		if(data.containsKey(Location.LABEL))
			location.setLabel(data.getString(Location.LABEL));
		
		if(data.containsKey(Location.LEAVES)){
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			try {
				location.setLeaves(sdf.parse(data.getString(Location.LEAVES)));
			} catch (ParseException e) {
				Log.e(TAG, e.getMessage());
			}
		}
		
		if(data.containsKey(Location.OFFSET))
			location.setOffset(data.getInt(Location.OFFSET));
		
		if(data.containsKey(Location.POINT)){
			String point_type = data.getString(Location.POINT);
			if (point_type.equals(Location.POINT_TYPE[Location.ORIG]))
				location.setPoint(Location.ORIG);
			else if (point_type.equals(Location.POINT_TYPE[Location.DEST]))
				location.setPoint(Location.DEST);
			else if (point_type.equals(Location.POINT_TYPE[Location.WAYP]))
				location.setPoint(Location.WAYP);
			else if (point_type.equals(Location.POINT_TYPE[Location.POSI]))
				location.setPoint(Location.POSI);
		}
		
		if(data.containsKey(Location.POSTCODE))
			location.setPostcode(data.getInt(Location.POSTCODE));
		
		if(data.containsKey(Location.RECURS))
			location.setRecurs(data.getString(Location.RECURS));
		
		if(data.containsKey(Location.REGION))
			location.setRegion(data.getString(Location.REGION));
		
		if(data.containsKey(Location.STREET))
			location.setStreet(data.getString(Location.STREET));
		
		if(data.containsKey(Location.SUBREGION))
			location.setSubregion(data.getString(Location.SUBREGION));
		
		if(data.containsKey(Location.TOWN))
			location.setTown(data.getString(Location.TOWN));
		
		return location;
	}
}
