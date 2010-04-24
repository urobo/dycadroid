/**
 * 
 */
package eu.fbk.dycapo;

import java.util.Date;

import android.util.Log;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.xmlrpc.XMLRPCClient;
import eu.fbk.dycapo.xmlrpc.XMLRPCException;

/**
 * @author riccardo
 *
 */

public class Test implements Runnable {
	
	private String method;
	private Location fbk;
	private Person user;
	
	/**
	 * @param method
	 */
	public Test(String method) {
		this.method = method;
    	this.fbk = new Location("fbk", 3,"46.462822799999998, 11.3343092",
    			10, new Date());
    	this.user = new Person("rider1", "rico.sleeps@gmail.com","password",
			"riccardo", "buttarelli", "http://", "3409289053",fbk
			, 21,"male",true,true,true,true);
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
		while(attempts<3){
			try {
			XMLRPCClient client = new XMLRPCClient("http://test.dycapo.org/RPC2",user.getUsername(), user.getPassword());
		    	if(this.method instanceof java.lang.String){
		    		Object p= new Object();
		    		if (this.method== "dycapo.get_position"){
		    			p= (Object) client.call(this.method,user.toHashMap());
		    		}else if (this.method== "dycapo.update_position"){
		    			p= (Object) client.call(this.method,this.fbk.toHashMap());
		    		}
		    		Log.i("dycapo", new Integer(attempts).toString()+ " " + this.method);
		    		Log.i("dycapo response", p.toString());
		    	}
				
			} catch (XMLRPCException e) {
				Log.e("XMLRPCException", e.getMessage());
				
			} catch (Exception e){
				Log.e("Exception", e.getMessage());
					
			} finally {
				attempts++;
			}
		}

	}

}
