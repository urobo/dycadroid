/**
 * 
 */
package eu.fbk.dycapo.services;

/**
 * @author riccardo
 *
 */
public final class Dycapo {
	public static final String DYCAPO_URL="http://test.dycapo.org";
	
	public static final String DYCAPO_REGISTRATION_USERNAME = "user";
	public static final String DYCAPO_REGISTRATION_PASSWORD = "password";
	
	public static final String[] DYCAPO_METHODS={
		
		"dycapo.accept_ride_request",	//	dycapo.accept_ride_request ( Trip trip , Person person )
		"dycapo.add_trip",				//	dycapo.add_trip ( Trip trip , Mode mode , Prefs preferences , Location source , Location destination )
		"dycapo.add_trip_exp", 			// 	dycapo.add_trip_exp ( Trip trip )
		"dycapo.cancel_requested_ride",	//	dycapo.cancel_requested_ride ( Trip trip )
		"dycapo.change_password",		//	dycapo.change_password ( Person person )
		
		"dycapo.check_requested_ride",	//	dycapo.check_requested_ride ( Trip trip )
		"dycapo.check_ride_requests",	//	dycapo.check_ride_requests ( Trip trip )
		"dycapo.finish_ride",			//	dycapo.finish_ride ( Trip trip )
		"dycapo.finish_trip",			//	dycapo.finish_trip ( Trip trip )
		"dycapo.get_position",			//	dycapo.get_position ( Person person )
		
		"dycapo.refuse_ride_request",	//	dycapo.refuse_ride_request ( Trip trip , Person person )
		"dycapo.register",				//	dycapo.register ( Person person )
		"dycapo.request_ride",			//	dycapo.request_ride ( Trip trip )
		"dycapo.search_trip",			//	dycapo.search_trip ( Location source , Location destination )
		"dycapo.start_ride",			//	dycapo.start_ride ( Trip trip )
		
		"dycapo.start_trip",			//	dycapo.start_trip ( Trip trip )
		"dycapo.update_position"		//	dycapo.update_position ( Location position )
		};
	
	public static final int ACCEPT_RIDE_REQUEST = 0;
	
	public static final int ADD_TRIP = 1;
	
	public static final int ADD_TRIP_EXP = 2;
	
	public static final int CANCEL_REQUESTED_RIDE = 3;
	
	public static final int CHANGE_PASSWORD = 4;
	
	public static final int CHECK_REQUESTED_RIDE = 5;
	
	public static final int CHECK_RIDE_REQUESTS = 6;
	
	public static final int FINISH_RIDE = 7;
	
	public static final int FINISH_TRIP = 8;
	
	public static final int GET_POSITION = 9;
	
	public static final int REFUSE_RIDE_REQUEST = 10;
	
	public static final int REGISTER = 11;
	
	public static final int REQUEST_RIDE = 12;
	
	public static final int SEARCH_TRIP = 13;
	
	public static final int START_RIDE = 14;
	
	public static final int START_TRIP = 15;
	
	public static final int UPDATE_POSITION = 16;
	
	public static String getMethod (int method){
		if (method >= 0 && method < Dycapo.DYCAPO_METHODS.length) return Dycapo.DYCAPO_METHODS[method];
		return null;
	}
}
