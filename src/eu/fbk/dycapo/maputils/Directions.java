/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.location.Address;
import android.util.Log;

import com.google.android.maps.MapView;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.persistency.ActiveTrip;
import eu.fbk.dycapo.persistency.DBTrip;


/**
 * @author riccardo
 */
public final class Directions{
	
	private static StringBuilder sb = null;
	private static InputStream is = null;
	private static final String TAG = "Directions";

	
	public static final void getRoute (Address src,Address dest,Context ctx)
    { 
		//http://maps.google.com/maps/api/directions/json?origin=Via+Sommarive,Trento&destination=Viale+Europa,170,39100+Bolzano+BZ&sensor=true
		sb = new StringBuilder();
		sb.append("http://maps.google.com/maps/api/directions/json?");
		
		sb.append("origin=");
		sb.append(src.getAddressLine(0).replace(" ", "+"));
		sb.append(",");
		sb.append(src.getAddressLine(1).replace(" ", "+"));
		sb.append("&destination=");
		sb.append(dest.getAddressLine(0).replace(" ", "+"));
		sb.append(",");
		sb.append(dest.getAddressLine(1).replace(" ", "+"));
		sb.append("&sensor=true");
		
		String url = sb.toString();
		Log.d(TAG, url);
		HttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(httpget);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			DirectionsResponseParser.parseDirections(is);
			is.close();
		} catch (ClientProtocolException e) {
			
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (DycapoException e) {
			Log.e(TAG, e.getMessage());
		}
    }
	
	public static void drawPath(MapView mMapView01) throws DycapoException{
		ActiveTrip aTrip = DBTrip.getActiveTrip();
		if (aTrip.getRoute().getmDecodedPolyline() != null)
		mMapView01.getOverlays().add(new DycapoOverlay(aTrip.getRoute().getmDecodedPolyline()));
		mMapView01.getController().animateTo(aTrip.getRoute().getmDecodedPolyline().get(0));
	}
	
	public static void getComplexRoute(Address source,Address dest, Address wayps,Context ctx){
		
	}

}
