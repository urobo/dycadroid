/**
 * 
 */
package eu.fbk.dycapo.maputils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.location.Address;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.persistency.ActiveTrip;
import eu.fbk.dycapo.persistency.DBTrip;
import eu.fbk.dycapo.persistency.Route;


/**
 * @author riccardo
 *  Directions Response Elements
Directions responses contain two root elements:
"status" contains metadata on the request. See Status Codes below.
"routes" contains an array of routes from the origin to the destination. See Routes below.
Routes consist of nested Legs and Steps.
Status Codes
The "status" field within the Directions response object contains the status of the request, and may contain debugging information to help you track down why the Directions service failed. The "status" field may contain the following values:
OK indicates the response contains a valid result.
NOT_FOUND indicates at least one of the locations specified in the requests's origin, destination, or waypoints could not be geocoded.
ZERO_RESULTS indicates no route could be found between the origin and destination.
MAX_WAYPOINTS_EXCEEDED indicates that too many waypointss were provided in the request The maximum allowed waypoints is 8, plus the origin, and destination. ( Google Maps Premier customers may contain requests with up to 23 waypoints.)
INVALID_REQUEST indicates that the provided request was invalid.
OVER_QUERY_LIMIT indicates the service has received too many requests from your application within the allowed time period.
REQUEST_DENIED indicates that the service denied use of the directions service by your application.
UNKNOWN_ERROR indicates a directions request could not be processed due to a server error. The request may succeed if you try again.
Routes
When the Directions API returns results, it places them within a (JSON) routes array. Even if the service returns no results (such as if the origin and/or destination doesn't exist) it still returns an empty routes array. (XML responses consist of zero or more <route> elements.)
Each element of the routes array contains a single result from the specified origin and destination. This route may consist of one or more legs depending on whether any waypoints were specified. As well, the route also contains copyright and warning information which must be displayed to the user in addition to the routing information.
Each route within the routes field may contain the following fields:
summary contains a short textual description for the route, suitable for naming and disambiguating the route from alternatives.
legs[] contains an array which contains information about a leg of the route, between two locations within the given route. A separate leg will be present for each waypoint or destination specified. (A route with no waypoints will contain exactly one leg within the legs array.) Each leg consists of a series of steps. (See Directions Legs below.)
waypoint_order contains an array indicating the order of any waypoints in the calculated route. This waypoints may be reordered if the request was passed optimize:true within its waypoints parameter.
overview_path contains an object holding an array of encoded points and levels that represent an approximate (smoothed) path of the resulting directions.
copyrights contains the copyrights text to be displayed for this route. You must handle and display this information yourself.
warnings[] contains an array of warnings to be displayed when showing these directions. You must handle and display these warnings yourself.
Legs
Each element in the legs array specifies a single leg of the journey from the origin to the destination in the calculated route. For routes that contain no waypoints, the route will consist of a single "leg," but for routes that define one or more waypoints, the route will consist of one or more legs, corresponding to the specific legs of the journey.
Each leg within the legs field(s) may contain the following fields:
steps[] contains an array of steps denoting information about each separate step of the leg of the journey. (See Directions Steps below.)
distance indicates the total distance covered by this leg, as a field with the following elements:
value indicates the distance in meters
text contains a human-readable representation of the distance, displayed in units as used at the origin, in the language specified in the request. (For example, miles and feet will be used for any origin within the United States.) Note that regardless of what unit system is displayed as text, the distance.value field always contains a value expressed in meters.
These fields may be absent if the distance is unknown.
duration indicates the total duration of this leg, as a field with the following elements:
value indicates the duration in seconds.
text contains a human-readable representation of the duration.
These fields may be absent if the duration is unknown.
start_location contains the latitude/longitude coordinates of the origin of this leg. Because the Directions API calculates directions between locations by using the nearest transportation option (usually a road) at the start and end points, start_location may be different than the provided origin of this leg if, for example, a road is not near the origin.
end_location contains the latitude/longitude coordinates of the given destination of this leg. Because the Directions API calculates directions between locations by using the nearest transportation option (usually a road) at the start and end points, end_location may be different than the provided destination of this leg if, for example, a road is not near the destination.
start_address contains the human-readable address (typically a street address) reflecting the start_location of this leg.
end_addresss contains the human-readable address (typically a street address) reflecting the end_location of this leg.
Steps
Each element in the steps array defines a single step of the calculated directions. A step is the most atomic unit of a direction's route, containing a single step describing a specific, single instruction on the journey. E.g. "Turn left at W. 4th St." The step not only describes the instruction but also contains distance and duration information relating to how this step relates to the following step. For example, a step denoted as "Merge onto I-80 West" may contain a duration of "37 miles" and "40 minutes," indicating that the next step is 37 miles/40 minutes from this step.
Each step within the steps field(s) may contain the following fields:
html_instructions contains formatted instructions for this step, presented as an HTML text string.
distance contains the distance covered by this step until the next step. (See the discussion of this field in Directions Legs above.) This field may be undefined if the distance is unknown.
duration contains the typical time required to perform the step, until the next step (See the description in Directions Legs above.) This field may be undefined if the duration is unknown.
start_location contains the location of the starting point of this step, as a single set of lat and lng fields.
end_location contains the location of the starting point of this step, as a single set of lat and lng fields.
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
			e.alertUser(ctx);
		}
    }
	
	public static void drawPath(int color, MapView mMapView01) throws DycapoException{
		ActiveTrip aTrip = DBTrip.getActiveTrip();
		Route mRoute = aTrip.getRoute();
		List<GeoPoint> path = PolylineDecoder.decodePolyline(mRoute.getmPolyline());
		int size = path.size();
		for (int i = 0 ; i < size ; i++){
			Log.d(TAG, path.get(i).toString());
		}
	}
	

   
    
    
//	HttpEntity entity= response.getEntity();
//	is = entity.getContent();
//	InputStreamReader isr = new InputStreamReader(is);
//	XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
//	XmlPullParser xpp = xppf.newPullParser();
//	xpp.setInput(isr);
//	int eventType = xpp.getEventType();
//	while (eventType!=XmlPullParser.END_DOCUMENT){
//		switch (eventType){
//		case XmlPullParser.START_DOCUMENT:
//			Log.d(TAG,"Start Document");
//			break;
//		case XmlPullParser.END_DOCUMENT:
//			Log.d(TAG,"End Document");
//			break;
//		case XmlPullParser.START_TAG:
//			Log.d(TAG, "Opening : "+xpp.getName());
//			break;
//		case XmlPullParser.END_TAG:
//			Log.d(TAG, "Closing : "+xpp.getName());
//			break;
//		case XmlPullParser.TEXT:
//			Log.d(TAG, "Text : " + xpp.getText());
//			break;
//		}
//		eventType = xpp.next();
//	}
}
