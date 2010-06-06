/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.persistency.ActiveTrip;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.persistency.Route;

/**
 * @author riccardo
 *
 */
public final class DirectionsResponseParser {
	
	private static final String TAG="DirectionsResponseParser";
	
	private static final String[] KEYS = {
		"status",
		"routes",
		"overview_polyline",
		"duration",
		"points",
		"value",
		"legs",
		"steps"
	};
	
	private static final String[] RESPONSE_STATUS ={
		"OK" ,						// indicates the response contains a valid result.
		"NOT_FOUND" , 				// indicates at least one of the locations specified in the requests's origin, destination, or waypoints could not be geocoded.
		"ZERO_RESULTS" ,			// indicates no route could be found between the origin and destination.
		"MAX_WAYPOINTS_EXCEEDED" ,	// indicates that too many waypoints were provided in the request The maximum allowed waypoints is 8, plus the origin, and destination. ( Google Maps Premier customers may contain requests with up to 23 waypoints.)
		"INVALID_REQUEST" , 		// indicates that the provided request was invalid.
		"OVER_QUERY_LIMIT" , 		// indicates the service has received too many requests from your application within the allowed time period.
		"REQUEST_DENIED" , 			// indicates that the service denied use of the directions service by your application.
		"UNKNOWN_ERROR"
	};
	
	private static final String[] ERROR_MESSAGES ={
		"OK" ,								
		"Wrong Locations supplied" , 		
		"There isn't an actual route between the give Locations" ,			
		"Number of Waypoints provided exceeds the allowed" ,	
		"Request is Invalid" , 		
		"Number of Queries exceeds the allowed Queries per day number" , 	
		"Service Rejected the Request" , 			
		"Unknown Error"
	};
	
	private static final int OK = 0;
	private static final int NOT_FOUND = 1;
	private static final int ZERO_RESULTS = 2; 
	private static final int MAX_WAYPOINTS_EXCEEDED = 3; 
	private static final int INVALID_REQUEST = 4;
	private static final int OVER_QUERY_LIMIT = 5;
	private static final int REQUEST_DENIED = 6;
	private static final int UNKNOWN_ERROR = 7;
	
	private static final int STATUS = 0;
	private static final int ROUTES = 1;
	private static final int OVERVIEW_POLYLINE = 2;
	private static final int DURATION = 3;
	private static final int POINTS = 4;
	private static final int VALUE = 5;
	private static final int LEGS = 6;
	@SuppressWarnings("unused")
	private static final int STEPS = 7;
	
	
	private static String getKey(int key){
		if (key >= 0 && key < KEYS.length)return KEYS[key];
		return null;
	}
	private static String resolveStatus(int key){
		if (key >= 0 && key < RESPONSE_STATUS.length)return RESPONSE_STATUS[key];
		return null;
	}
	private static String resolveErrorMessage(int key){
		if (key >= 0 && key < ERROR_MESSAGES.length)return ERROR_MESSAGES[key];
		return null;
	}
	
	public static void parseDirections(InputStream is) throws DycapoException{

		String directionsResponse = DirectionsResponseParser.convertStreamToString(is);
		JSONObject json;
		try {
			json = new JSONObject (directionsResponse);
	
			
			String status = json.getString(getKey(STATUS));
			Log.d(TAG + " : " + getKey(STATUS) , status);
			if (status.equals(resolveStatus(OK))){
				
				JSONArray routes = json.getJSONArray(getKey(ROUTES));
				JSONObject mainRoute = routes.getJSONObject(0);
				JSONObject overviewPolyline = mainRoute.getJSONObject(getKey(OVERVIEW_POLYLINE)); // getting the polyline
				
				
				
				JSONArray legs = mainRoute.getJSONArray(getKey(LEGS));
				int size = legs.length();
				
				int totalDurationSecs = 0;
				for (int i = 0 ; i < size ; i++){	
					totalDurationSecs += legs.getJSONObject(i).getJSONObject(getKey(DURATION)).getInt(getKey(VALUE)); // getting total duration
				}
				
				ActiveTrip aTrip = DBTrip.getActiveTrip();
				Route mRoute = new Route();
			
				mRoute.setmDecodedPolyline(PolylineDecoder.decodePolyline(overviewPolyline.getString(getKey(POINTS))));
				mRoute.setmDurationSecs(totalDurationSecs);
				mRoute.setmStatusCode(status);
				
				aTrip.setRoute(mRoute);
				DBTrip.saveActiveTrip(aTrip);
				
				json = null;
				mRoute = null;
				
			}else {
				String errorMsg = null;
				if (status.equals(resolveStatus(NOT_FOUND)))
					errorMsg = resolveErrorMessage(NOT_FOUND);
				else if (status.equals(resolveStatus(ZERO_RESULTS)))
					errorMsg = resolveErrorMessage(ZERO_RESULTS);
				else if (status.equals(resolveStatus(MAX_WAYPOINTS_EXCEEDED)))
					errorMsg = resolveErrorMessage(MAX_WAYPOINTS_EXCEEDED);
				else if (status.equals(resolveStatus(INVALID_REQUEST)))
					errorMsg = resolveErrorMessage(INVALID_REQUEST);
				else if (status.equals(resolveStatus(OVER_QUERY_LIMIT)))
					errorMsg = resolveErrorMessage(OVER_QUERY_LIMIT);
				else if (status.equals(resolveStatus(REQUEST_DENIED)))
					errorMsg = resolveErrorMessage(REQUEST_DENIED);
				else if (status.equals(resolveStatus(UNKNOWN_ERROR)))
					errorMsg = resolveErrorMessage(UNKNOWN_ERROR);
				throw new DycapoException (TAG + " : " +errorMsg);
			}
			
		} catch (JSONException e) {
			Log.e(TAG,e.getMessage());
		}
	}
	
	private static String convertStreamToString(InputStream is) {
	        /*
	         * To convert the InputStream to String we use the BufferedReader.readLine()
	         * method. We iterate until the BufferedReader return null which means
	         * there's no more data to read. Each line will appended to a StringBuilder
	         * and returned as String.
	         */
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        StringBuilder sb = new StringBuilder();
	 
	        String line = null;
	        try {
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	                Log.d(TAG, line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return sb.toString();
	    }
}
