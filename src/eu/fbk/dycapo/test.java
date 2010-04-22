package eu.fbk.dycapo;

import java.net.MalformedURLException;
import java.util.Date;
import eu.fbk.dycapo.models.Location;
import eu.fbk.dycapo.models.Person;
import eu.fbk.dycapo.xmlrpc.XMLRPCClient;
import eu.fbk.dycapo.xmlrpc.XMLRPCException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class test extends Activity implements OnClickListener{
	
	    /** Called when the activity is first created. */
    @Override
  
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try{
        	Button testupdate = (Button) findViewById(R.id.Button01);
        	testupdate.setOnClickListener((OnClickListener)this);
        	Button testget = (Button) findViewById (R.id.Button02);
        	testget.setOnClickListener((OnClickListener)this);
        	}catch (Exception e){
        		Log.e("exception", e.getMessage());
        	}
        }

    public void xmlrpc_call_update(int attempt) throws MalformedURLException, XMLRPCException{
	   
    	Location fbk = new Location("fbk", 3,"46.462822799999998, 11.3343092",
    			10, new Date());
    	Person user = new Person("rider1", "rico.sleeps@gmail.com","password",
			"riccardo", "buttarelli", "http://", "3409289053",fbk
			, 21,"male",true,true,true,true);
    	
    	XMLRPCClient client = new XMLRPCClient("http://test.dycapo.org/RPC2",user.getUsername(), user.getPassword());
    	client.setBasicAuthentication(user.getUsername(),user.getPassword());
    	//Object p= (Object) client.call("dycapo.get_position",user.toHashMap());
    	Object j= (Object) client.call("dycapo.update_position",fbk.toHashMap());
    	//Log.i("Response",p.toString());
    	Log.i("dycapo.update_position", new Integer(attempt).toString());
    	Log.i("fbk", j.toString());
       	
   }

    public void xmlrpc_call_get(int attempt) throws MalformedURLException, XMLRPCException{
    	Location fbk = new Location("fbk", 3,"46.462822799999998, 11.3343092",
    			10, new Date());
    	Person user = new Person("rider1", "rico.sleeps@gmail.com","password",
			"riccardo", "buttarelli", "http://", "3409289053",fbk
			, 21,"male",true,true,true,true);
    	
    	XMLRPCClient client = new XMLRPCClient("http://test.dycapo.org/RPC2",user.getUsername(), user.getPassword());
    	//client.setBasicAuthentication(user.getUsername(),user.getPassword());
    	Object p= (Object) client.call("dycapo.get_position",user.toHashMap());
    	//Object j= (Object) client.call("dycapo.update_position",fbk.toHashMap());
    	//Log.i("Response",p.toString());
    	Log.i("dycapo.get_position", new Integer(attempt).toString());
    	Log.i("fbk", p.toString());
    }
    
    public void onClick(View v){

    		switch (v.getId()){
    		case R.id.Button01:		
    			int attempt=0;
    			while(attempt<3){
    				try {
    					this.xmlrpc_call_update(attempt);
    				} catch (MalformedURLException e) {
    					Log.e("MalformedURLException", e.getMessage());
						
    				} catch (XMLRPCException e) {
    					Log.e("XMLRPCException", e.getMessage());
    					
    				} catch (Exception e){
    					Log.e("Exception", e.getMessage());
    						
    				} finally {
    					attempt++;
    				}
    			}
    			break;
    			
    		case R.id.Button02:
    			int attempts=0;
    			while(attempts<3){
    				try {
    					this.xmlrpc_call_get(attempts);
    				} catch (MalformedURLException e) {
    					Log.e("MalformedURLException", e.getMessage());
						
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
}