/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.Date;
import java.util.HashMap;

import eu.fbk.dycapo.models.Location;

/**
 * @author riccardo
 *
 */
public class LocationFetcher {
	public static Location fetchLocation(HashMap<String,Object> value){
		Location result= new Location();
		String point = (String)value.get(Location.POINT);
		if (point==Location.POINT_TYPE[0])result.setPoint(0);
		else if (point==Location.POINT_TYPE[1])result.setPoint(1);
		else if (point==Location.POINT_TYPE[2])result.setPoint(2);
		else if (point==Location.POINT_TYPE[3])result.setPoint(3);
		
		result.setLeaves(new Date(value.get(Location.LEAVES).toString()));
		// TODO if chain for common may/should param
		
		if (value.containsKey(Location.GEORSS_POINT)){
			result.setGeorss_point((String) value.get(Location.GEORSS_POINT));

		} else {
			//TODO otherwise contains street town postcode
		}
		return null;
	}
}
