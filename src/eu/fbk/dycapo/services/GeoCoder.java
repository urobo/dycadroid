/**
 * 
 */
package eu.fbk.dycapo.services;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import eu.fbk.dycapo.exceptions.DycapoException;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;


/**
 * @author riccardo
 *
 */
public class GeoCoder{
	
	public static List<Address> getLocation(Context ctx,String inputAddress) throws DycapoException {
				List<Address> foundAddresses=null;
				Geocoder gc = new Geocoder(ctx, Locale.ITALIAN);
				try {
					Log.d("Looking for", inputAddress);
					foundAddresses = gc.getFromLocationName(inputAddress, 3);
					if (!foundAddresses.isEmpty()){
						for (int i=0; i<foundAddresses.size();i++){
							Log.d("Address"+ String.valueOf(i) , foundAddresses.get(i).getAddressLine(0)+foundAddresses.get(i).getAddressLine(1));
						}
					}else Log.d("Address", "foundAddresses is empty");
				} catch (IOException e) {
					Log.e("GeoCoder", e.getMessage());
					throw new DycapoException ("GeoCoder.getLocation : "+e.getMessage());
				} // Search addresses
				return foundAddresses;
	}
}