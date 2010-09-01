/**
 * 
 */
package eu.fbk.dycapo.services;

/**
 * @author riccardo
 *
 */
public final class Dycapo {
	public static final String DYCAPO_URL="http://test.dycapo.org/RPC2";
	//public static final String DYCAPO_URL = "http://172.10.0.2/RPC2";
	//public static final String DYCAPO_URL="http://10.7.199.77/RPC2";
	
	public static final String DYCAPO_REGISTRATION_USERNAME = "register";
	public static final String DYCAPO_REGISTRATION_PASSWORD = "register";
	
	public static final String[] DYCAPO_METHODS={
		
		"dycapo.acceptRide",	//	dycapo.accept_ride_request ( Trip trip , Person person )
		"dycapo.insertTrip",				//	dycapo.add_trip ( Trip trip , Mode mode , Prefs preferences , Location source , Location destination )
		"dycapo.cancelRide",	//	dycapo.cancel_requested_ride ( Trip trip )
		"dycapo.changePassword",		//	dycapo.change_password ( Person person )
		
		"dycapo.statusRequestedRide",	//	dycapo.check_requested_ride ( Trip trip )
		"dycapo.getRides",	//	dycapo.check_ride_requests ( Trip trip )
		"dycapo.finishRide",			//	dycapo.finish_ride ( Trip trip )
		"dycapo.finishTrip",			//	dycapo.finish_trip ( Trip trip )
		"dycapo.getPosition",			//	dycapo.get_position ( Person person )
		
		"dycapo.refuseRide",	//	dycapo.refuse_ride_request ( Trip trip , Person person )
		"dycapo.register",				//	dycapo.register ( Person person )
		"dycapo.requestRide",			//	dycapo.request_ride ( Trip trip )
		"dycapo.searchRide",			//	dycapo.search_trip ( Location source , Location destination )
		"dycapo.startRide",			//	dycapo.start_ride ( Trip trip )
		
		"dycapo.startTrip",			//	dycapo.start_trip ( Trip trip )
		"dycapo.setPosition"		//	dycapo.update_position ( Location position )
		};
	
	public static final int ACCEPT_RIDE_REQUEST = 0;
	
	public static final int ADD_TRIP = 1;
	
	public static final int CANCEL_REQUESTED_RIDE = 2;
	
	public static final int CHANGE_PASSWORD = 3;
	
	public static final int CHECK_REQUESTED_RIDE = 4;
	
	public static final int CHECK_RIDE_REQUESTS = 5;
	
	public static final int FINISH_RIDE = 6;
	
	public static final int FINISH_TRIP = 7;
	
	public static final int GET_POSITION = 8;
	
	public static final int REFUSE_RIDE_REQUEST = 9;
	
	public static final int REGISTER = 10;
	
	public static final int REQUEST_RIDE = 11;
	
	public static final int SEARCH_TRIP = 12;
	
	public static final int START_RIDE = 13;
	
	public static final int START_TRIP = 14;
	
	public static final int UPDATE_POSITION = 15;
	
	public static String getMethod (int method){
		if (method >= 0 && method < Dycapo.DYCAPO_METHODS.length) return Dycapo.DYCAPO_METHODS[method];
		return null;
	}
}
