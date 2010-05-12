/**
 * 
 */
package eu.fbk.dycapo.test;

import java.util.Date;

import android.util.Log;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.exceptions.Tag;
import eu.fbk.dycapo.factories.DycapoObjectsFetcher;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Mode;
import eu.fbk.dycapo.models.Preferences;
import eu.fbk.dycapo.models.Trip;
import eu.fbk.dycapo.persistency.User;
import eu.fbk.dycapo.xmlrpc.XMLRPCClient;
import eu.fbk.dycapo.xmlrpc.XMLRPCException;

/**
 * @author riccardo
 *
 */

public class Test implements Runnable {
	public static final int ATTEMPS=1;
	public static final String[] METHODS={
											"dycapo.get_position",
											"dycapo.update_position",
											"dycapo.add_trip",
											"dycapo.search_trip",
											"dycapo.start_trip",
											"dycapo.request_ride",
											"dycapo.check_ride_requests",
											"dycapo.accept_ride_request"
											};
	
	private String method;
	private Location fbk;
	private Location fbk1;
	private Location bodomsHome;
	private User user;
	private static Trip trip;
	private Date now;
	
	/**
	 * @param method
	 */
	public Test(String method) {
		
		this.method = method;
		
		this.now=new Date();
    	
		this.fbk = new Location(
    								"fbk", 
    								2,
    								"46.0670192, 11.1505136",
    								10, 
    								this.now
    								);
		
		this.fbk1 = new Location(
								"fbk", 
								1,
								"46.0670192, 11.1505136",
								10, 
								this.now
								);
		
		this.bodomsHome = new Location (
										"daniel's home",
										0,
										"46.462822799999998, 11.3343092",
										10,
										this.now
										);
    	
    	this.user = new User(
    								"rider1", 
    								"rico.sleeps@gmail.com",
    								"password",
    								"riccardo",
    								"buttarelli", 
    								"http://", 
    								"3409289053",
    								fbk, 
    								21,
    								"male",
    								true,true,true,true
    								);
    	

    	
    	if (!(Test.trip instanceof Trip))Test.trip = new Trip(
    			this.now,
    			this.user,
    			new Mode("van", 
    					new Integer(5), 
    					new Integer(4), 
    					"audi",
    					"A4",
    					new Integer(1998)),
    			new Preferences(
    					"",
					false,
					Preferences.BOTH,
					true,
					true),
				this.bodomsHome,
				this.fbk1
				);
    	
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		int attempts=0;
		while(attempts<Test.ATTEMPS){
			try {
			XMLRPCClient client = new XMLRPCClient("http://test.dycapo.org/RPC2",user.getUsername(), user.getPassword());
		    	if(this.method instanceof java.lang.String){
		    		Object p= new Object();
		    		
		    		//dycapo.get_position
		    		if (this.method.equals(Test.METHODS[0])){
		    			p= (Object) client.call(this.method,user.toHashMap());
		    			
		    			
		    		}//dycapo.update_position
		    		else if (this.method.equals(Test.METHODS[1])){
		    			p= (Object) client.call(this.method,this.fbk.toHashMap());
		    		
		    		
		    		}//dycapo.add_trip ( Trip trip , Mode mode , Prefs preferences , Location source , Location destination )
		    		else if (this.method.equals(Test.METHODS[2])){
		    			p= (Object) client.call( 	this.method,
		    										Test.trip.toHashMap(), 
		    										Test.trip.getMode().toHashMap(),
		    										Test.trip.getPreferences().toHashMap(),
		    										Test.trip.getOrigin().toHashMap(),
		    										Test.trip.getDestination().toHashMap()
		    										);
		    			
		    		}//dycapo.search_trip
		    		else if (this.method.equals(Test.METHODS[3])){
		    			p= (Object) client.call( 	this.method,
		    										Test.trip.getOrigin().toHashMap(),
		    										Test.trip.getDestination().toHashMap());
		    			
		    		}//dycapo.start_trip
		    		else if (this.method.equals(Test.METHODS[4])){
		    			p= (Object) client.call( 	this.method,
		    										Test.trip.toHashMap());
		    			
		    		}//dycapo.request_ride
		    		else if (this.method.equals(Test.METHODS[5])){
		    			p= (Object) client.call( 	this.method,
													Test.trip.toHashMap());
	
		    		}else if (this.method.equals(Test.METHODS[6])){
		    			p= (Object) client.call( 	this.method,
													Test.trip.toHashMap());

		    		}else if (this.method.equals(Test.METHODS[7])){
		    		
		    			p= (Object) client.call(	this.method,
		    										Test.trip.toHashMap(),
		    										this.user.toHashMap());
		    		return;
	
		    		}else throw new DycapoException("Invalid method passed");
		    		
		    		p= DycapoObjectsFetcher.fetchXMLRPCResponse(p);
		    		if(p instanceof Location) Log.i("Location", ((Location) p).getLabel()+ " " +((Location) p).getGeorss_point() +" " +  ((Location) p).getPoint());
		    		else if(p instanceof Boolean) Log.i("Boolean", p.toString());
		    		else if(p instanceof Trip) {
		    			Test.trip.setId(((Trip)p).getId());
		    			Log.d(Tag.LOG+"."+Tag.DYCAPOTRIP, ((Trip)p).getId().toString());
		    		}
		    		else Log.i("search_trip", p.toString());
		    		Log.i("Response",p.toString());
		    		Log.i("dycapo", new Integer(attempts).toString()+ " " + this.method);
		    		Log.i("dycapo response", p.toString());
		       }
			} catch (DycapoException e){
				Log.e("DycapoException", e.getMessage());
				
			} catch (XMLRPCException e) {
				Log.e("XMLRPCException", e.getMessage());
				
			} finally {
				attempts++;
			}
		}

	} 

}
