/**
 * 
 */
package eu.fbk.dycapo.services;
/**
 * @author riccardo
 *
 */
public abstract class DycapoServerCall {
	public static String[] DYCAPO_METHODS ={
		"dycapo.accept_ride_request",		// dycapo.accept_ride_request ( Trip trip , Person person )
		"dycapo.add_trip",					// dycapo.add_trip ( Trip trip , Mode mode , Prefs preferences , Location source , Location destination )
		"dycapo.check_ride_requests",		// dycapo.check_ride_requests ( Trip trip )
		"dycapo.finish_ride",				// dycapo.finish_ride ( Trip trip )
		"dycapo.finish_trip",				// dycapo.finish_trip ( Trip trip )
		"dycapo.get_position",				// dycapo.get_position ( Person person )
		"dycapo.request_ride",				// dycapo.request_ride ( Trip trip )
		"dycapo.search_trip",				// dycapo.search_trip ( Location source , Location destination )
		"dycapo.start_ride",				// dycapo.start_ride ( Trip trip )
		"dycapo.update_position" 			// dycapo.start_trip ( Trip trip )
	};
	
	public static int ACCEPT_RIDE_REQUEST = 0;
	public static int ADD_TRIP = 1;
	public static int CHECK_RIDE_REQUESTS = 2;
	public static int FINISH_RIDE = 3;
	public static int FINISH_TRIP = 4;
	public static int GET_POSITION = 5;
	public static int REQUEST_RIDE = 6;
	public static int SEARCH_TRIP = 7;
	public static int START_RIDE = 8;
	public static int UPDATE_POSITION = 9;
}
