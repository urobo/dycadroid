/**
 * 
 */
package eu.fbk.dycapo.factories.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Location;

/**
 * @author riccardo
 *
 */
public abstract class LocationFetcher {
	private static final String TAG ="LocationFetcher";
	public static final Location fetchLocation(JSONObject responseValue) throws DycapoException{
		try{	
			String message="error LocationFetcher.fetchLocation : not enough parameters are given to define a location: missing ";
			Location result= new Location();
			
			if (responseValue.has(DycapoObjectsFetcher.HREF))
				result.setHref(responseValue.getString(DycapoObjectsFetcher.HREF));
			
			if (responseValue.has(Location.POINT)){
				String point = responseValue.getString(Location.POINT);
				
				if (point.equals(Location.POINT_TYPE[0]))result.setPoint(0);
				else if (point.equals(Location.POINT_TYPE[1]))result.setPoint(1);
				else if (point.equals(Location.POINT_TYPE[2]))result.setPoint(2);
				else if (point.equals(Location.POINT_TYPE[3]))result.setPoint(3);
			} else new DycapoException(message + Location.POINT);
			
			SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

			if (responseValue.has(Location.LEAVES))
			{
				try {
					result.setLeaves(parser.parse(responseValue.getString(Location.LEAVES)));
				} catch (ParseException e) {
					Log.e(TAG, e.getMessage());
					throw new DycapoException(e.getMessage());
				}
				result.setLeaves((Date)responseValue.get(Location.LEAVES));
			}
			else new DycapoException(message + Location.LEAVES);
			
			if(responseValue.has(Location.LABEL))
				result.setLabel(responseValue.getString(Location.LABEL));
			
			if (responseValue.has(Location.COUNTRY))
				result.setCountry(responseValue.getString(Location.COUNTRY));
			
			if (responseValue.has(Location.REGION))
				result.setRegion(responseValue.getString(Location.REGION));
			
			if (responseValue.has(Location.SUBREGION))
				result.setSubregion(responseValue.getString(Location.SUBREGION));
			
			if (responseValue.has(Location.RECURS))
				result.setRecurs(responseValue.getString(Location.RECURS));
			
			if (responseValue.has(Location.DAYS))
				result.setDays(responseValue.getString(Location.DAYS));
			
			if (responseValue.has(Location.OFFSET))
				result.setOffset(responseValue.getInt(Location.OFFSET));
			
			// if location defined via georss_point
			if (responseValue.has(Location.GEORSS_POINT)){
				result.setGeorss_point(responseValue.getString(Location.GEORSS_POINT));
			} else {
				message += Location.GEORSS_POINT + "or provide ";
				// else it must contain street,town and postcode
				// if all street, town and postcode are present
				if (	responseValue.has(Location.STREET) &&
						responseValue.has(Location.TOWN) &&
						responseValue.has(Location.POSTCODE)  ){
					result.setStreet(responseValue.getString(Location.STREET));
					result.setTown(responseValue.getString(Location.TOWN));
					result.setPostcode(responseValue.getInt(Location.POSTCODE));
				}else{ 
					//else if one is missing error message composed and an exception will be raised
					if (!responseValue.has(Location.STREET))
						message+= " " + Location.STREET;
					if (!responseValue.has(Location.TOWN))
						message+= " " + Location.TOWN;
					if (!responseValue.has(Location.POSTCODE));
						message+= " " + Location.POSTCODE;
					throw new DycapoException (message);
				}	
			}
			return result;
		}catch(JSONException e){
			e.printStackTrace();
		}
			return null;
	}
}
