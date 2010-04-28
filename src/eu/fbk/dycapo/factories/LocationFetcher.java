/**
 * 
 */
package eu.fbk.dycapo.factories;

import java.util.Date;
import java.util.HashMap;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.models.Location;

/**
 * @author riccardo
 *
 */
public class LocationFetcher {
	public static Location fetchLocation(HashMap<String,Object> value) throws DycapoException{
			
			String message="error LocationFetcher.fetchLocation : not enough parameters are given to define a location: missing ";
			Location result= new Location();
			
			if (value.containsKey(Location.POINT)){
				String point = (String)value.get(Location.POINT);
				if (point==Location.POINT_TYPE[0])result.setPoint(0);
				else if (point==Location.POINT_TYPE[1])result.setPoint(1);
				else if (point==Location.POINT_TYPE[2])result.setPoint(2);
				else if (point==Location.POINT_TYPE[3])result.setPoint(3);
			} else new DycapoException(message + Location.POINT);
			
			if (value.containsKey(Location.LEAVES))
				result.setLeaves((Date)(value.get(Location.LEAVES)));
			else new DycapoException(message + Location.LEAVES);
			
			if(value.containsKey(Location.LABEL))
				result.setLabel((String)value.get(Location.LABEL));
			
			if (value.containsKey(Location.COUNTRY))
				result.setCountry((String)value.get(Location.COUNTRY));
			
			if (value.containsKey(Location.REGION))
				result.setRegion((String)value.get(Location.REGION));
			
			if (value.containsKey(Location.SUBREGION))
				result.setSubregion((String)value.get(Location.SUBREGION));
			
			if (value.containsKey(Location.RECURS))
				result.setRecurs((String)value.get(Location.RECURS));
			
			if (value.containsKey(Location.DAYS))
				result.setDays((String)value.get(Location.DAYS));
			
			// if location defined via georss_point
			if (value.containsKey(Location.GEORSS_POINT)){
				result.setGeorss_point((String) value.get(Location.GEORSS_POINT));
			} else {
				message += Location.GEORSS_POINT + "or provide ";
				// else it must contain street,town and postcode
				// if all street, town and postcode are present
				if (value.containsKey(Location.STREET)&&value.containsKey(Location.TOWN)&&value.containsKey(Location.POSTCODE)){
					result.setStreet((String)value.get(Location.STREET));
					result.setTown((String)value.get(Location.TOWN));
					result.setPostcode(Integer.parseInt(value.get(Location.POSTCODE).toString()));
				}else{ 
					//else if one is missing error message composed and an exception will be raised
					if (!value.containsKey(Location.STREET))
						message+= " " + Location.STREET;
					if (!value.containsKey(Location.TOWN))
						message+= " " + Location.TOWN;
					if (!value.containsKey(Location.POSTCODE));
						message+= " " + Location.POSTCODE;
					throw new DycapoException (message);
				}	
			}
			
			return result;

	}
}
